// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.delete;

import org.springframework.stereotype.Service;

/**
 * API利用者グループ削除APIのビジネスロジックを実装するServiceインターフェース.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/12
 */
@Service
public interface IDelApiGroupsService {

    /**
     * API利用者グループ情報を削除するメソッド.
     * 
     * @param servicerGroupId API利用者グループID.
     */
    int deleteData(String servicerGroupId);

}
