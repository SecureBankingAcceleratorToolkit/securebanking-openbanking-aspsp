## SBA Toolkit's Open Banking ASPSP

A multi-module maven project providing a mock Open Banking ASPSP for the Secure Banking Accelerator Toolkit.

### Setting up Maven

Download and install Maven settings.xml file by running the command below and substituting in your backstage username and password.

```bash
curl -u $BACKSTAGE_USERNAME http://maven.forgerock.org/repo/private-releases/settings.xml > ~/.m2/settings.xml
```

### Build the project

#### Maven build

From the command line, simply run:

```bash
mvn clean install
```

This will run any JUnit/Spring integration tests and build the required JAR file and docker image.

### How to run
Either run the docker image created in the previous step, or run the project's SpringBoot application class:

```com.forgerock.securebanking.openbanking.aspsp.AspspSimulatorApplication``` 

Note that the application has a dependency on MongoDB and will not start up without it. If running locally, this can be
achieved by simply starting up Mongo on its default port (27017) - for example by running a MongoDB docker image.

### Supported APIs
Upon starting the application, a list of supported APIs can be obtained dynamically from two different endpoints:

1. Discovery Endpoint
1. Swagger Specification Endpoint
 
#### Discovery Endpoint
The application has a "Discovery Endpoint" which lists all the supported URLs. This is the Open Banking Read/Write API
that the application has implemented. The Discovery Endpoint can be viewed in a browser by visiting:

```http://<host>:<port>/open-banking/discovery```

> Substitute `<host>` and `<port` as necessary

#### Swagger Specification Endpoint
The application's swagger documentation can be obtained from the following URL:

```http://<host>:<port>/api-docs``` 

> Substitute `<host>` and `<port` as necessary

This provides the full swagger specification, including the Open Banking Read/Write Apis that the application is able
to support (regardless of whether they have been disabled in the configuration). Importantly, this reveals any
additional headers or request parameters that are required by the simulator.

> Any additional headers/parameters will be provided automatically to a bank's ASPSP, if it is used instead
>of ForgeRock's ASPSP simulator).

#### Enable/Disable API Endpoints
By default, all implemented API endpoints are enabled, however it is possible to explicitly disable them in the
application's config.

```
aspsp:
  discovery:
    financialId: 0015800001041REAAY
    versions:
      # v3.0 to v3.1.4 are enabled by default but set to `true` here for completeness
      v3.0: true
      v3.1: true
      v3.1.1: true
      v3.1.2: true
      v3.1.3: true
      v3.1.4: true

      # Disable all v3.1.5 and v3.1.6 endpoints
      v3.1.5: false
      v3.1.6: false

    apis:
      # Disable Create/Get DomesticPaymentConsent across all versions
        CreateDomesticPaymentConsent: false      
        GetDomesticPaymentConsent: false

    versionApiOverrides:
      # Disable Get statement endpoints in v3.1.3 and v3.1.4
      v3_1_3:
        GetStatements: false
        GetAccountStatements: false
      v3_1_4:
        GetStatements: false
        GetAccountStatements: false
```

As can be deduced from this config, Read/Write API endpoints can be disabled (resulting in a 404 error response) by one
of three ways:

1. **versions**: By specifying a complete version of the API to block. In the above example, any requests to v3.1.4
or v3.1.5 will be blocked.
1. **apis**: By specifying the name of the endpoint (which can be derived from the Discovery endpoint). In the above
example, `CreateDomesticPaymentConsent` and `GetDomesticPaymentConsent` are disabled in all versions.
1. **versionApiOverrides**: Or more specifically, by listing both the version and name of the endpoint. In the above
example, `GetStatements` and `GetAccountStatements` will be blocked in v3.1.3 and v3.1.4, but will work in the versions
prior to this.
