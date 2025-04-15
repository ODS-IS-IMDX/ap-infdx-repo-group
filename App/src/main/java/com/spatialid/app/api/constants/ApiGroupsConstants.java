// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.constants;

/**
 * API利用者グループの定数を管理するクラス.
 *
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
public class ApiGroupsConstants {

    /**
     * API利用者グループ関連APIのベースURI.
     */
    public static final String API_GROUPS_BASS_URI = "/gen/api/generic/v1";

    /**
     * API利用者グループ参照APIのURI.
     */
    public static final String API_GROUPS_GET_URI = "/api-groups";

    /**
     * API利用者グループ登録APIのURI.
     */
    public static final String API_GROUPS_REG_URI = "/api-groups";

    /**
     * API利用者グループ更新APIのURI.
     */
    public static final String API_GROUPS_UPD_URI = "/api-groups/{servicerGroupId}";

    /**
     * API利用者グループ削除APIのURI.
     */
    public static final String API_GROUPS_DEL_URI = "/api-groups/{servicerGroupId}";

    /**
     * 日付形式チェックで用いる詳細な日付フォーマット
     */
    public static final String DETAIL_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    /** 
     * トランザクション競合エラーを捕捉する為に使用する文字列. 
     */
    public static final String CONFLICT_ERROR_MESSAGE = "expired or aborted by a conflict: 40001";

}
