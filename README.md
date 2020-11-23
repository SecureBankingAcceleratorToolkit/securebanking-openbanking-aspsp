## SBA Toolkit's Open Banking ASPSP

A multi-module maven project providing a mock Open Banking ASPSP for the Secure Banking Accelerator Toolkit.

### Setting up Maven

Download and install Maven settings.xml file by running the command below and substituting in your backstage username and password.

```bash
curl -u $BACKSTAGE_USERNAME http://maven.forgerock.org/repo/private-releases/settings.xml > ~/.m2/settings.xml
```

### Build the project

#### Compile

From the command line, simply run:

```bash
mvn clean install
```

This will run any JUnit/Spring integration tests and build the required JAR files and docker images.

#### Swagger Code Generation
The project is set-up to make it easy to generate the OB model classes and skeleton API classes.
For efficiency, the default maven profile does not generate the code, but it is simple to do so by 
running the `code-gen` profile - for example:

```bash
mvn clean install -Pcode-gen
```

This will generate classes from the swagger specification into `securebanking-openbanking-aspsp-mock/target/generated-sources/swagger`.
From here, the generated classes can be copied to the source folder under `src/main/java`.

> CAUTION: When copying the newly generated files, be careful not to overwrite existing classes.

This is because most of them have been generated using `swagger-codegen-cli-2.4.5.jar`, which is older than the
`openapi-generator` maven plugin we are using. Also, we use a shared generic version of `Links`, `Meta`, `OBError1` and
`OBErrorResponse1`.

Note that the OB Read/Write API consists of several yaml files, however the `openapi-generator-maven-plugin` currently
only generates code from one swagger file. Because of this, each individual yaml file is listed within the plugin and should
be commented/uncommented as necessary.

The configuration for the swagger generation is currently within `securebanking-openbanking-aspsp-mock/pom.xml` 
and the swagger specification is within `securebanking-openbanking-aspsp-mock/src/main/resources/specification`.
