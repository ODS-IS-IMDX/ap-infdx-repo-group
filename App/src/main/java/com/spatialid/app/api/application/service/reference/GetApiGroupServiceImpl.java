// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.domain.repository.ApiGroupsRepository;
import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsRequestDto;
import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsResponseDto;
import com.spatialid.app.api.presentation.dto.reference.ServicerGroupDto;

/**
 * API利用者グループ参照APIのビジネスロジックを実装するServiceクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/11
 */
@Service
public class GetApiGroupServiceImpl implements IGetApiGroupsService {

    /**
     * API利用者グループ関連APIのrepositoryインターフェース.
     */
    ApiGroupsRepository apiGroupsRepository;

    /**
     * コンストラクタインジェクション.
     * 
     * @param apiGroupsRepository API利用者グループ関連APIのRepositoryインターフェース.
     */
    public GetApiGroupServiceImpl(ApiGroupsRepository apiGroupsRepository) {

        this.apiGroupsRepository = apiGroupsRepository;
    }

    @Override
    public GetApiGroupsResponseDto getApiGroupsInfo(GetApiGroupsRequestDto requestDto) {

        // エンティティを設定する.
        ApiGroupsEntity requestEntity = ApiGroupsEntity.builder()
                .servicerGroupId(requestDto.getServicerGroupId())
                .servicerId(requestDto.getServicerId())
                .build();

        // API利用者グループ情報リストを取得する.
        List<ApiGroupsEntity> responseEntityList = apiGroupsRepository.getData(requestEntity);

        // レスポンスを設定する.
        List<String> servicerIdList = new ArrayList<>();

        List<ServicerGroupDto> servicerGroupList = new ArrayList<>();

        for (ApiGroupsEntity responseEntity : responseEntityList) {

            servicerIdList.add(responseEntity.getServicerId());

            servicerGroupList.add(ServicerGroupDto.builder()
                    .servicerGroupId(responseEntity.getServicerGroupId())
                    .servicerGroupName(responseEntity.getServicerGroupName())
                    .overview(responseEntity.getOverview())
                    .build());

        }
        
        // 各リストの重複を排除する.
        List<String> distinctServicerIdList = servicerIdList.stream()
                .distinct()
                .collect(Collectors.toList());

        List<ServicerGroupDto> distinctServicerGroupList = servicerGroupList.stream()
                .distinct()
                .collect(Collectors.toList());

        GetApiGroupsResponseDto responseDto = GetApiGroupsResponseDto.builder()
                .servicerIdList(distinctServicerIdList)
                .servicerGroupList(distinctServicerGroupList)
                .build();

        return responseDto;
    }

}
