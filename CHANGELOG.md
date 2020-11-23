# Git Changelog Maven plugin changelog
Changelog of Git Changelog Maven plugin.
## Unreleased
### GitHub [#3](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/3) 2: Converted to a multi-module project. Added generated OB data model
[2d45a0a3748c4e9](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/2d45a0a3748c4e9) Matt Wills *2020-11-19 13:06:49*
2: Convert to multi-module project (#3)

- Add OB model objects to forgerock-sba-ob-model
- Add ability to generate OB model objects using maven profile
Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
### GitHub [#4](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/4) Added initial controllers and integration tests
[c41cf79572181e6](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/c41cf79572181e6) Matt Wills *2020-11-20 17:21:18*
2: Add initial controllers and integration tests (#4)

- Add v3.1.5 of Domestic Payment controllers
- Add SpringBoot tests using an embedded mongo
- Add Test Data Factories and test-jar generation
- Update jackson/joda libraries
- Add converter methods to reduce number of assertions
- Rename module to securebanking-openbanking-aspsp-simulator

Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
[b4d0e28645d0542](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/b4d0e28645d0542) Matt Wills *2020-11-19 13:05:05*
2: Initial SpringBoot project

- Add SpringBoot capability and initial test to verify the start up
- Add FR and OB domain models
- Add mongo documents and repositories
- Add OB model to/from FR domain converters
- Add services and common utilities

**Issue:** https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
