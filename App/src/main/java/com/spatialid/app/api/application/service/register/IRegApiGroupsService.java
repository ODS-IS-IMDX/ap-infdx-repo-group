// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.register;

import java.util.List;

import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.presentation.dto.register.RegApiGroupsRequestDto;

/**
 * API利用者グループ登録APIのビジネスロジックを実装するServiceインターフェース.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
public interface IRegApiGroupsService {

    /**
     * リクエスト内の利用者システムIDがDB内に存在するものであるかをチェックするメソッド.
     * 
     * @param servicerIdList 利用者システムIDリスト.
     * @return boolean 在否.
     */
    boolean isServicerIdExist(List<String> servicerIdList);

    /**
     * 利用者グループIDシーケンステーブルから一意のIDを採番するメソッド.
     * 
     * @return String　シーケンスID.
     */
    String getSequencialId();

    /**
     * データ登録の為のEntityクラスを設定するメソッド.
     * 
     * @param regApiGroupsRequestDto リクエスト情報.
     * @param sequencialId シーケンス番号.
     * @return List<RegApiGroupsEntity>　利用者グループ情報リスト.
     */
    List<ApiGroupsEntity> setEntity(RegApiGroupsRequestDto regApiGroupsRequestDto, String sequencialId);

    /**
     * 設定したEntityクラスを元にAPI利用者グループテーブルにデータを登録するメソッド.
     * 
     * @param regApiGroupsEntityList データ登録用利用者グループ情報リスト.
     */
    void registerServicerGroup(List<ApiGroupsEntity> regApiGroupsEntityList);
}
