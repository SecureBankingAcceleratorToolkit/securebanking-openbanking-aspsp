/**
 * Copyright 2020 ForgeRock AS.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.sba.ob.aspsp.persistence.repository.accounts.beneficiaries;

import com.forgerock.sba.ob.aspsp.persistence.document.account.FRBeneficiary;
import com.forgerock.sba.ob.aspsp.persistence.domain.account.common.FRExternalPermissionsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FRBeneficiaryRepositoryImpl implements FRBeneficiaryRepositoryCustom {
    @Autowired
    @Lazy
    private FRBeneficiaryRepository beneficiaryRepository;

    @Override
    public Page<FRBeneficiary> byAccountIdWithPermissions(String accountId, List<FRExternalPermissionsCode> permissions,
                                                          Pageable pageable) {
        return filter(beneficiaryRepository.findByAccountId(accountId, pageable), permissions);
    }

    @Override
    public Page<FRBeneficiary> byAccountIdInWithPermissions(List<String> accountIds, List<FRExternalPermissionsCode> permissions,
                                                            Pageable pageable) {
        return filter(beneficiaryRepository.findByAccountIdIn(accountIds, pageable), permissions);
    }

    private Page<FRBeneficiary> filter(Page<FRBeneficiary> beneficiaries, List<FRExternalPermissionsCode> permissions) {
        for (FRExternalPermissionsCode permission : permissions) {
            switch (permission) {

                case READBENEFICIARIESBASIC:
                    for (FRBeneficiary beneficiary : beneficiaries) {
                        beneficiary.getBeneficiary().setCreditorAccount(null);
                    }
                    break;
            }
        }
        return beneficiaries;
    }
}
