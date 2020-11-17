/**
 * Copyright 2019 ForgeRock AS.
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.openbanking.aspsp.persistence.repository.accounts.statements;

import com.forgerock.openbanking.aspsp.persistence.document.account.FRStatement;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.forgerock.openbanking.aspsp.persistence.domain.account.common.FRExternalPermissionsCode;

import java.util.List;

public interface FRStatementRepositoryCustom {

    Page<FRStatement> byAccountIdWithPermissions(
            String accountId,
            DateTime fromStatementDateTime,
            DateTime toStatementDateTime,
            List<FRExternalPermissionsCode> permissions,
            Pageable pageable);

    List<FRStatement> byAccountIdAndStatementIdWithPermissions(
            String accountId,
            String statementId,
            List<FRExternalPermissionsCode> permissions
    );

    Page<FRStatement> byAccountIdInWithPermissions(
            List<String> accountIds,
            List<FRExternalPermissionsCode> permissions,
            Pageable pageable);
}
