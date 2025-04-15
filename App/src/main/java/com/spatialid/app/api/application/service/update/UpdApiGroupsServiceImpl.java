// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatialid.app.api.constants.ApiGroupsConstants;
import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.domain.repository.ApiGroupsRepository;
import com.spatialid.app.api.presentation.dto.update.UpdApiGroupsRequestDto;
import com.spatialid.app.common.exception.subexception.ExclusiveErrorException;

/**
 * API利用者グループ更新APIのビジネスロジックを実装するServiceクラス.
 * 
 * @auther ukai jun
 * @version 1.1 2024.11.26
 */
@Service
public class UpdApiGroupsServiceImpl implements IUpdApiGroupsService {

    /**
     * API利用者グループ関連APIのRepositoryインターフェース.
     */
    private final ApiGroupsRepository repository;

    /**
     * コンストラクタインジェクション.
     * 
     * @param apiGroupsRepository Repositoryインターフェース.
     */
    public UpdApiGroupsServiceImpl(ApiGroupsRepository apiGroupsRepository) {

        this.repository = apiGroupsRepository;
    }

    @Override
    public int countRegisteredData(String servicerGroupId) {
        
        int dataCount = repository.countByServicerGroupId(servicerGroupId);
        
        return dataCount;
    }

    @Override
    public List<ApiGroupsEntity> setEntity(UpdApiGroupsRequestDto requestDto, String servicerGroupId) {

        List<ApiGroupsEntity> apiGroupsEntityList = new ArrayList<>();

        // 更新日時として使用する為に、現在日時を取得する.
        String updateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(ApiGroupsConstants.DETAIL_DATE_FORMAT));

        // 利用者システムIDリストの件数分エンティティを作成、リストに追加し返却する.
        for (String servicerId : requestDto.getServicerIdList()) {

            ApiGroupsEntity apiGroupsEntity = ApiGroupsEntity.builder()
                    .servicerGroupId(servicerGroupId)
                    .servicerGroupName(requestDto.getServicerGroupName())
                    .servicerId(servicerId)
                    .overview(requestDto.getOverview())
                    .updateTime(updateTime)
                    .build();

            apiGroupsEntityList.add(apiGroupsEntity);
        }

        return apiGroupsEntityList;
    }

    @Override
    public LocalDateTime getLatestUpdateTime(String servicerGroupId) {

        // 登録されている最新の更新日時を取得する.
        LocalDateTime latestUpdateTime = repository.getLatestUpdateTime(servicerGroupId);

        return latestUpdateTime;
    }

    @Override
    @Transactional
    public void updateServicerGroup(List<ApiGroupsEntity> apiGroupsEntityList, String servicerGroupId, int dataCount,
            LocalDateTime latestUpdateTime) {

        // API利用者グループIDと最新更新日時を元に、既存データを削除する.
        int delResult = repository.deleteDataWithUpdateTime(servicerGroupId, latestUpdateTime);

        // 更新が差し挟まれ、削除できなかった場合は排他チェックエラーとする.
        if (delResult != dataCount) {

            throw new ExclusiveErrorException();

        } else {

            // 設定した利用者グループ情報リストを新しいデータとして登録する.
            repository.registerDataWithUpdateTime(apiGroupsEntityList);

        }

    }

}
