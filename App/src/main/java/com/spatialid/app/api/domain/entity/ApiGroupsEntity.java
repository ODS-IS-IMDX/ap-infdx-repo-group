// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API利用者グループ関連APIにおいてビジネスロジックで使用するEntityクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class ApiGroupsEntity {

    /**
     * 利用者グループID.
     */
    private String servicerGroupId;
    
    /**
     * 利用者グループ名.
     */
    private String servicerGroupName;
    
    /**
     * 利用者システムID.
     */
    private String servicerId;
    
    /**
     * 概要.
     */
    private String overview;
    
    /**
     * 更新日時.
     */
    private String updateTime;
}
