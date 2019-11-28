# hyperon_runtime_tutorial
This project was created for developers, who would like to learn more about Hyperon Runtime library.

All code examples are in package "src\test\java".
It is recommended to do the exercises in the right order, from basic to advanced. Test cases are grouped
logically and each starts with usage examples. At the end of each test group there are tests prefixed
"todo_" and @Disabled annotation, which contain exercises.

In this project the Hyperon Runtime connects to H2 database file located in /db sub-directory of the root
project directory. While doing the exercises you should run Hyperon Studio connected to this database,
to review relevant data. The version of Hyperon Studio should match the version of Hyperon Runtime.
The configuration file for the studio should point to database "hyperon.demo.motor.h2" from this project.

Hyperon Studio can be downloaded from https://hyperon.io/download.

[[INFO]] WHILE GOING THROUGH EXAMPLES, BE LOGGED IN HYPERON STUDIO [[/INFO]]

Learning order (group by package):
1. "intro"
    a) ParameterTest
    b) FunctionTest
    c) DomainTest
2. "parameters"
    a) SimpleTypesParameterTest
    b) MatrixParameterTest
    c) ArraysInMatrixParameterTest
    d) NullableValueInMatrixParameterTest
    e) CascadeCallParameterTest
3. "functions"
    a) CallWithArgumentFunctionTest
    b) ComplexFunctionWithNestedCallsTest
    c) ExternalCallAsParameterFunctionTest
4. "domain"
    a) SimpleDomainObjectTest
    b) ComplexAttributeDomainObjectTest
    c) CollectionDomainObjectTest
    d) ReferenceDomainObjectTest
    e) DomainTypeTest
5. "administration"
    a) UsingEngineInDevmodeTest
6. "context"
    a) SimpleCustomContextWithAdaptersTest
    b) ContextWithCollectionAdaptersTest


Extra info: Make sure to play around with any code, debug, explore and read javadoc of library.
We'll appreciate any feedback.
