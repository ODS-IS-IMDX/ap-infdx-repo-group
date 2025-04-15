// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.update;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.presentation.dto.update.UpdApiGroupsRequestDto;

/**
 * API利用者グループ更新APIのビジネスロジックを管理するServiceインターフェース.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Service
public interface IUpdApiGroupsService {

    /**
     * リクエストされた利用者グループIDがDB内に存在するかを確かめるメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @return 件数.
     */
    int countRegisteredData(String servicerGroupId);

    /**
     * 更新処理に使用するEntityクラスを設定するメソッド.
     * 
     * @param requestDto      API利用者グループ更新APIが受け取ったリクエストDTO.
     * @param servicerGroupId 利用者グループID.
     * @return API利用者グループ情報リスト.
     */
    List<ApiGroupsEntity> setEntity(UpdApiGroupsRequestDto requestDto, String servicerGroupId);

    /**
     * 利用者グループIDを元に、登録されている最新の更新日時を取得するメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @return 最新更新日時
     */
    public LocalDateTime getLatestUpdateTime(String servicerGroupId);
    
    /**
     * API利用者グループ情報を更新し、排他チェックを行うメソッド.
     * 
     * @param apiGroupsEntity API利用者グループエンティティ.
     * @param servicerGroupId 利用者グループID.
     * @param dataCount 該当データ件数.
     * @param latestUpdateTime 最新更新日時.
     */
    void updateServicerGroup(List<ApiGroupsEntity> apiGroupsEntity, String servicerGroupId, int dataCount,
            LocalDateTime latestUpdateTime);

}
