# Higson Runtime tutorial
This project was created for developers, who would like to learn more about Higson Runtime library.

All code examples are in package <code>src\test\java</code>.
It is recommended to do the exercises in the right order, from basic to advanced. Test cases are grouped
logically and each starts with usage examples. At the end of each test group there are tests prefixed
<code>todo_</code> and <code>@Disabled</code> annotation, which contain exercises.

In this project the Higson Runtime connects to H2 database file located in /db sub-directory of the root
project directory. While doing the exercises you should run Higson Studio connected to this database,
to review relevant data. The version of Higson Studio should match the version of Higson Runtime.
The configuration file for the studio should point to database <code>hyperon.demo.motor.h2</code> from this project.

Higson Studio can be downloaded from [here](https://www.higson.io/download).  
In the application.properties of downloaded project please point an `hyperon.database.url` property   
to `./db/hyperon.demo.motor.mv.db` file. Later, run a `bin/catalina.sh run` command.

##### WHILE GOING THROUGH EXAMPLES, BE LOGGED IN HIGSON STUDIO

Learning order (group by package):

&#9888; Notice: You can find equivalents of below classes in `kotlin` classpath, those classes ends with **KtTest**.

##### 1."Intro"
    a) ParameterTest
    b) FunctionTest
    c) DomainTest
    
##### 2. "Parameters"
    a) SimpleTypesParameterTest
    b) MatrixParameterTest
    c) ArraysInMatrixParameterTest
    d) NullableValueInMatrixParameterTest
    e) CascadeCallParameterTest
    
##### 3. "Functions"
    a) CallWithArgumentFunctionTest
    b) ComplexFunctionWithNestedCallsTest
    c) ExternalCallAsParameterFunctionTest
    
##### 4. "Domain"
    a) SimpleDomainObjectTest
    b) ComplexAttributeDomainObjectTest
    c) CollectionDomainObjectTest
    d) ReferenceDomainObjectTest
    e) DomainTypeTest
    
##### 5. "Administration"
    a) UsingEngineInDevmodeTest
    
##### 6. "Context"
    a) SimpleCustomContextWithAdaptersTest
    b) ContextWithCollectionAdaptersTest


##### Extra info: Make sure to play around with any code, debug, explore and read javadoc of library.

We'll appreciate any feedback.
