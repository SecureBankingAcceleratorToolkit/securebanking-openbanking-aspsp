[<img src="https://raw.githubusercontent.com/ForgeRock/forgerock-logo-dev/master/Logo-fr-dev.png" align="right" width="220px"/>](https://developer.forgerock.com/)

# Financial Data Exchange Model
Java based model classes for the Financial Data Exchange (FDX) API, generated from the swagger specification
This project is compiled with JDK 11.

## Usage
```
<dependency>
    <groupId>com.forgerock.fdx</groupId>
    <artifactId>fdx-model</artifactId>
    <version>${fdx-model.version}</version>
</dependency>
```

## Class generation
Many of the classes are generated from Swagger documentation. When a new version of API is released, 
the following steps are performed:
### Install swagger codegen cli
#### On MacOS
1. Install swagger codegen cli using brew
```
brew install swagger-codegen
```
1. Run
```
swagger-codegen generate \
-i {your_json_file or URL} \
-DuseBeanValidation=true -Dmodel --model-package com.forgerock.financial.data.exchange.model.{version vx_y_z} \
--group-id com.forgerock.fdx --artifact-id fdx-model -l spring --library spring-boot -o generated
```
#### Other systems
1. Download swagger codegen cli from
>https://swagger.io/tools/swagger-codegen/download
1. Run
```
java -jar swagger-codegen-cli-x.x.x.jar generate \
-i {your_json_file or URL} \
-DuseBeanValidation=true -Dmodel --model-package com.forgerock.financial.data.exchange.model.{version vx_y_z} \
--group-id com.forgerock.fdx --artifact-id fdx-model -l spring --library spring-boot -o generated
```
1. Check the generated files and copy them into appropriate source directory. Do not overwrite existing files.
1. Repeat generation for each new swagger json file
1. If using Intelij, run format and optimise imports on newly generated files. 
1. Increment the major or minor version in pom.xml
1. Run build to ensure everything compiles and copyrights are generated for new source files.
1. Commit and raise PR.  
