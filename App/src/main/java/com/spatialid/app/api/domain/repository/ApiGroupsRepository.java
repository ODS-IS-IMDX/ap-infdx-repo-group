// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spatialid.app.api.domain.entity.ApiGroupsEntity;

/**
 * API利用者グループ関連APIのDB操作を管理するRepositoryインターフェース.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Mapper
public interface ApiGroupsRepository {

    /**
     * API利用者グループ情報を取得するメソッド.
     * 
     * @param entity API利用者グループ情報.
     * @return API利用者グループ情報リスト.
     */
    public List<ApiGroupsEntity> getData(ApiGroupsEntity entity);

    /**
     * 最新の更新日時を取得するメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @return 更新日時.
     */
    public LocalDateTime getLatestUpdateTime(String servicerGroupId);

    /**
     * 利用者グループIDシーケンステーブルからIDを採番するメソッド.
     * 
     * @return シーケンス値.
     */
    public String getSequence();

    /**
     * API利用者グループエンティティ情報リストを登録するメソッド.
     * <p>
     * 登録処理中に取得した更新日時を登録する.
     * 
     * @param apiGroupsEntityList API利用者グループ情報リスト.
     */
    public void registerData(List<ApiGroupsEntity> apiGroupsEntityList);

    /**
     * API利用者グループエンティティ情報リストを登録するメソッド.
     * <p>
     * 事前に取得した更新日時を登録する.
     * 
     * @param apiGroupsEntityList API利用者グループ情報リスト.
     */
    public void registerDataWithUpdateTime(List<ApiGroupsEntity> apiGroupsEntityList);

    /**
     * API利用者グループIDを元に利用者グループテーブルの該当データを削除するメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @return 削除件数.
     */
    public int deleteData(String servicerGroupId);

    /**
     * API利用者グループIDと更新日時を元に利用者グループテーブルの該当データを削除するメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @param updateTime 排他制御用の更新日時.
     * @return 削除件数.
     */
    public int deleteDataWithUpdateTime(String servicerGroupId, LocalDateTime updateTime);

    /**
     * 利用者システムIDリストを元に、リスト内の利用者システムIDが登録されている件数を取得するメソッド.
     * 
     * @param servicerIdList 利用者システムIDリスト.
     * @return 取得件数.
     */
    public int countByServicerId(List<String> servicerIdList);

    /**
     * 利用者グループIDを元にAPI利用者グループ情報の登録されている件数を取得するメソッド.
     * 
     * @param servicerGroupId
     * @return 取得件数.
     */
    public int countByServicerGroupId(String servicerGroupId);

}
