// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.delete;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.domain.repository.ApiGroupsRepository;

/**
 * API利用者グループ削除APIのビジネスロジックを実装するServiceクラス.
 */
@Service
public class DelApiGroupsServiceImpl implements IDelApiGroupsService {

    /**
     * API利用者グループ関連APIのRepositoryインターフェース.
     */
    ApiGroupsRepository apiGroupsRepository;

    /**
     * コンストラクタインジェクション.
     * 
     * @param apiGroupsRepository
     */
    public DelApiGroupsServiceImpl(ApiGroupsRepository apiGroupsRepository) {

        this.apiGroupsRepository = apiGroupsRepository;

    }

    @Override
    public int deleteData(String servicerGroupId) {

    int deletedCount = apiGroupsRepository.deleteData(servicerGroupId);

        return deletedCount;
        
    }

}
