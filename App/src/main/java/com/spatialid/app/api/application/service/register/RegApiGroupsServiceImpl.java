// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.register;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.domain.repository.ApiGroupsRepository;
import com.spatialid.app.api.presentation.dto.register.RegApiGroupsRequestDto;

/**
 * API利用者グループ登録APIのビジネスロジックを実装するServiceクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Service
public class RegApiGroupsServiceImpl implements IRegApiGroupsService {

    /**
     * API利用者グループ関連APIのRepositoryインターフェース.
     */
    private final ApiGroupsRepository regApiGroupsRepository;

    /**
     * コンストラクタインジェクション.
     * 
     * @param regApiGroupsRepository
     */
    public RegApiGroupsServiceImpl(ApiGroupsRepository regApiGroupsRepository) {

        this.regApiGroupsRepository = regApiGroupsRepository;
    }

    @Override
    public boolean isServicerIdExist(List<String> servicerIdList) {

        // リクエストに含まれる利用者システムIDを用い、DB内の一致する利用者システムIDの件数を取得する.
        int count = regApiGroupsRepository.countByServicerId(servicerIdList);

        // リクエストに含まれる利用者システムIDの件数と、DBから取得した件数が異なる場合、falseを返す.
        if (count != servicerIdList.size()) {

            return false;

        }

        return true;
    }

    @Override
    public String getSequencialId() {

        // レフトパディングで5桁に調整したシーケンスIDを取得する.
        String sequence = regApiGroupsRepository.getSequence();

        return sequence;

    }

    @Override
    public List<ApiGroupsEntity> setEntity(RegApiGroupsRequestDto regApiGroupsRequestDto, String sequencialId) {

        List<ApiGroupsEntity> regApiGroupsEntityList = new ArrayList<>();

        // 利用者システム（配列）の要素毎に１セットのEntityを作成し、リストに追加する.
        for (String servicerId : regApiGroupsRequestDto.getServicerIdList()) {

            ApiGroupsEntity regApiGroupsEntity = ApiGroupsEntity.builder()
                    .servicerGroupId(sequencialId)
                    .servicerId(servicerId)
                    .servicerGroupName(regApiGroupsRequestDto.getServicerGroupName())
                    .overview(regApiGroupsRequestDto.getOverview())
                    .build();

            regApiGroupsEntityList.add(regApiGroupsEntity);
        }

        return regApiGroupsEntityList;
    }

    @Override
    public void registerServicerGroup(List<ApiGroupsEntity> regApiGroupsEntityList) {

        // 利用者グループ情報を登録する.
        regApiGroupsRepository.registerData(regApiGroupsEntityList);
    }
}
