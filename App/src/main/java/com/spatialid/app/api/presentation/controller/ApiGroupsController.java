// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spatialid.app.api.application.service.delete.IDelApiGroupsService;
import com.spatialid.app.api.application.service.reference.IGetApiGroupsService;
import com.spatialid.app.api.application.service.register.IRegApiGroupsService;
import com.spatialid.app.api.application.service.update.IUpdApiGroupsService;
import com.spatialid.app.api.constants.ApiGroupsConstants;
import com.spatialid.app.api.domain.entity.ApiGroupsEntity;
import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsRequestDto;
import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsResponseDto;
import com.spatialid.app.api.presentation.dto.register.RegApiGroupsRequestDto;
import com.spatialid.app.api.presentation.dto.register.RegApiGroupsResponseDto;
import com.spatialid.app.api.presentation.dto.update.UpdApiGroupsRequestDto;
import com.spatialid.app.common.exception.subexception.ExclusiveErrorException;
import com.spatialid.app.common.exception.subexception.NotFoundException;
import com.spatialid.app.common.exception.subexception.ParamErrorException;

/**
 * API利用者グループ関連APIのリクエストからレスポンスまでを管理するControllerクラス.
 * 
 * @author ukai jun
 * @version 1.1
 */
@RestController
@RequestMapping(ApiGroupsConstants.API_GROUPS_BASS_URI)
public class ApiGroupsController {

    /**
     * API利用者グループ登録APIサービスインターフェース.
     */
    private final IGetApiGroupsService getService;

    /**
     * API利用者グループ登録APIサービスインターフェース.
     */
    private final IRegApiGroupsService regService;

    /**
     * API利用者グループ更新APIサービスインターフェース.
     */
    private final IUpdApiGroupsService updService;

    /**
     * API利用者グループ更新APIサービスインターフェース.
     */
    private final IDelApiGroupsService delService;

    /**
     * コンストラクタインジェクション.
     * 
     * @param getApiGroupsService
     * @param regApiGroupsService
     * @param updApiGroupsService
     * @param updApiGroupsService
     */
    public ApiGroupsController(IGetApiGroupsService getApiGroupsService, IRegApiGroupsService regApiGroupsService,
            IUpdApiGroupsService updApiGroupsService, IDelApiGroupsService delApiGroupsService) {

        this.getService = getApiGroupsService;
        this.regService = regApiGroupsService;
        this.updService = updApiGroupsService;
        this.delService = delApiGroupsService;

    }

    /**
     * API利用者グループ参照APIの処理を管理するメソッド.
     * 
     * @param requestDto API利用者グループ参照が受け取るリクエストDTO.
     * @param br         リクエスト内容をバインドした際のエラー情報を格納するオブジェクト.
     * @return API利用者グループ参照が返却するレスポンスDTO.
     */
    @GetMapping(ApiGroupsConstants.API_GROUPS_GET_URI)
    public ResponseEntity<GetApiGroupsResponseDto> getApiGroups(
            @Validated @ModelAttribute GetApiGroupsRequestDto requestDto, BindingResult br) {

        // パラメータチェックを行う.
        if (br.hasErrors()) {

            throw new ParamErrorException(br);

        }

        // API利用者グループ情報を参照する.
        GetApiGroupsResponseDto responseDto = getService.getApiGroupsInfo(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);

    }

    /**
     * API利用者グループ登録APIの処理を管理するメソッド.
     * 
     * @param requestDto API利用者グループ登録APIが受け取るリクエストDTO.
     * @param br         リクエスト内容をバインドした際のエラー情報を格納するオブジェクト.
     * @return API利用者グループが返却するレスポンスDTO.
     */
    @PostMapping(ApiGroupsConstants.API_GROUPS_REG_URI)
    public ResponseEntity<RegApiGroupsResponseDto> registerApiGroups(
            @Validated @RequestBody RegApiGroupsRequestDto requestDto, BindingResult br) {

        // パラメータチェックを行う.
        if (br.hasErrors()) {

            throw new ParamErrorException(br);

        }

        List<String> servicerIdList = requestDto.getServicerIdList();

        // リクエストされた利用者システムIDがDB内に存在する事を確認する.
        boolean isIdExist = regService.isServicerIdExist(servicerIdList);

        if (!isIdExist) {

            Map<String, String> invalidValue = Map.of("servicerIdList", String.valueOf(servicerIdList));

            throw new ParamErrorException(invalidValue);

        }

        // シーケンスIDを採番する.
        String sequencialId = regService.getSequencialId();

        // 登録に使用するエンティティを設定する.
        List<ApiGroupsEntity> regApiGroupsEntityList = regService.setEntity(requestDto, sequencialId);

        // API利用者グループ情報をDBに登録する.
        regService.registerServicerGroup(regApiGroupsEntityList);

        // レスポンスデータ項目を設定する.
        RegApiGroupsResponseDto responseDto = RegApiGroupsResponseDto.builder()
                .servicerGroupId(sequencialId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);

    }

    /**
     * API利用者グループ更新APIの処理を管理するメソッド.
     * 
     * @param requestDto      API利用者グループ更新APIが受け取るリクエストDTO.
     * @param servicerGroupId 利用者グループID.
     * @param br              リクエストをバインドした結果のエラー情報を格納するオブジェクト.
     * @return HTTPステータスコード（204 No Content）.
     */
    @PutMapping({ ApiGroupsConstants.API_GROUPS_UPD_URI, ApiGroupsConstants.API_GROUPS_REG_URI + "/" })
    public ResponseEntity<Void> updateApiGroups(@PathVariable(required = false) String servicerGroupId,
            @Validated @RequestBody UpdApiGroupsRequestDto requestDto, BindingResult br) {

        // パラメータチェックを行う.
        if (StringUtils.isEmpty(servicerGroupId)) {

            Map<String, String> invalidValue = Map.of("servicerGroupId", String.valueOf(servicerGroupId));

            throw new ParamErrorException(invalidValue);

        } else if (br.hasErrors()) {

            throw new ParamErrorException(br);

        }

        List<String> servicerIdList = requestDto.getServicerIdList();

        // リクエストされた利用者システムIDが登録されている事を確認する.
        boolean isIdExist = regService.isServicerIdExist(servicerIdList);

        if (!isIdExist) {

            Map<String, String> invalidValue = Map.of("servicerIdList", String.valueOf(servicerIdList));

            throw new ParamErrorException(invalidValue);

        }

        // リクエストされた利用者グループIDが登録されている事を確認する.
        int dataCount = updService.countRegisteredData(servicerGroupId);

        if (dataCount == 0) {

            Map<String, String> hasNoData = Map.of("servicerGroupId", String.valueOf(servicerGroupId));

            throw new NotFoundException(hasNoData);

        }

        // 更新に使用するエンティティ情報を設定する.
        List<ApiGroupsEntity> apiGroupsEntityList = updService.setEntity(requestDto, servicerGroupId);

        // 最新更新日時を取得する.
        LocalDateTime latestUpdateTime = updService.getLatestUpdateTime(servicerGroupId);

        // API利用者グループ情報を更新し、排他チェックを行う.
        try {

            updService.updateServicerGroup(apiGroupsEntityList, servicerGroupId, dataCount, latestUpdateTime);

        } catch (Exception e) {

            if (e.getMessage() != null && e.getMessage()
                    .contains(ApiGroupsConstants.CONFLICT_ERROR_MESSAGE)) {

                throw new ExclusiveErrorException();

            } else {

                throw e;
            }
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();

    }

    /**
     * API利用者グループ削除APIの処理を管理するメソッド.
     * 
     * @param servicerGroupId 利用者グループID.
     * @return HTTPステータスコード（204 No Content）.
     */
    @DeleteMapping({ ApiGroupsConstants.API_GROUPS_DEL_URI, ApiGroupsConstants.API_GROUPS_REG_URI + "/" })
    public ResponseEntity<Void> delApiGroups(@PathVariable(required = false) String servicerGroupId) {

        // パラメータチェックを行う.
        if (StringUtils.isEmpty(servicerGroupId)) {

            Map<String, String> invalidValue = Map.of("servicerGroupId", String.valueOf(servicerGroupId));

            throw new ParamErrorException(invalidValue);

        }

        // API利用者グループ情報を削除する.
        int deletedCount = delService.deleteData(servicerGroupId);

        // 削除件数が0の場合、リソース無し例外を送出する.
        if (deletedCount == 0) {

            Map<String, String> invalidValue = Map.of("servicerGroupId", String.valueOf(servicerGroupId));

            throw new NotFoundException(invalidValue);

        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();

    }

}
