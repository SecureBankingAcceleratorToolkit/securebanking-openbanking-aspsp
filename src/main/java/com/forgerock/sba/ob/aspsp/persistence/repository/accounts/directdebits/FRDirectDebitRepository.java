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
package com.forgerock.sba.ob.aspsp.persistence.repository.accounts.directdebits;

import com.forgerock.sba.ob.aspsp.persistence.document.account.FRDirectDebit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface FRDirectDebitRepository extends MongoRepository<FRDirectDebit, String>, FRDirectDebitRepositoryCustom {

    Page<FRDirectDebit> findByAccountId(@Param("accountId") String accountId, Pageable pageable);

    Page<FRDirectDebit> findByAccountIdIn(@Param("accountIds") List<String> accountIds, Pageable pageable);

    Long deleteDirectDebitByAccountId(@Param("accountId") String accountId);

    Long countByAccountIdIn(Set<String> accountIds);
}
