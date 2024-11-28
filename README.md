Spring Boot Microservices
------------------------------
    Topics To Be Covered     
        Spring Boot initialization, Dependency Injection
        REST Web Service architecture , HTTP Endpoint handling, Exception Handling,
        
        Validating input request using javax.validation
      
        Eurekha Registry, Zuul Server / Spring Boot Api Gateway, Feign Client ,Ribbon / Spring Boot Load Balancer

    Lab Setup    
        JDK 17
        STS latest (IDE)
        Maven 3
        Spring 6 and Spring Boot 3

    Pre-Requisites
        Java 8
        JPA ORM
        API vs Framework vs Library
        Maven Build Tool

    Spring Framework

        is a java framework / development platform for developing Enterprise Apps.

        + It is highly modular
            Spring Core         is a basic must-have module for all other modules
            Spring Beans        offers basic DI with the help of a container called BeanFactory
            Spring Context      offers advanced DI with the help of a container called ApplicationContext
            Spring EL           Expression Language
            Spring Web          Supports developing Web MVC app and REST api
            Spring Data JPA     Provides dynamic auto-implemented calsses for the ORM interfaces
            Spring Boot         offers auto-config., channeling Rapid Application Development

            Spring Security     Supports autherization and authentication
            Spring Test         is a testing framework for any spring app
            Spring AOP          Aspect Oriented Programming
            Spring Batch        supports batch processing apps.
            .... etc.,

        + It is Interaperable

            Spring Framework allows non-spring based frameworks to be integrated with spring modules.

            for example, we need DI and Web MVC in an app,  
                generally we choose SpringCore + SpringContext + SpringWeb
                but it is also possible to choose SpringCore + SpringContext + Struts

        + It offers DI

            To achive higly maintanable products
                we have to ensure high cohesion and loose coupling.
                we achive this via..

                    1. Modularization               ensures cohesion
                    2. Multi-Layer archetecture     ensures loose coupling

                        an operation to search an employee given a employee id
                            (a) accept the meployee id
                            (b) validate the employee id
                            (c) if valid, query the database with the id
                            (d) if found, display the details
                            (e) if not found, diosplay a message 'not found'


                        DAO <--------model--------> SERVICE <--------model--------> UI
                                                                                    (a) accept the meployee id
                                                    (b) validate the employee id
                        (c) if valid, query the 
                            database with the id
                                                                                    (d) if found, display the details
                                                                                    (e) if not found, diosplay a message 'not found'

                        interface EmployeeDAO       interface EmployeeService       HRApplication
                        class EmployeeDAOImpl       class EmployeeServiceImpl

                        interface EmployeeDAO{
                            void add(Employee e);
                            void delete(long empId);
                        }

                        class EmployeeDAOImpl implements EmployeeDAO {

                            //a few methods to talk to the database like
                            // void add(Employee emp)
                            // void delete(long empId)
                        }

                        class EmployeeDAOJPAImpl implements EmployeeDAO {

                            //a few methods to talk to the database like
                            // void add(Employee emp)
                            // void delete(long empId)
                        }
                                               
                        class EmployeeServiceImpl {
                            
                            private EmployeeDao empDao;

                            public EmployeeServiceImpl(){
                                //this.empDao = new EmployeeDaoImpl();
                                this.empDao = new EmployeeDaoJpaImpl();
                            }
                            
                            //a few method to provide bussiness logic and call the dao methods when needed
                            // empDao.add(e)
                            // empDao.delete(id)
                        }

                    3. Dependency Injection         enhances loose coupling

                        Dependency is any software component (part) to be used.
                        Dependant is any software component that uses another component.

                        Service uses DAO, it means, Service is dependnat on DAO and DAO is the dependency of the Service.

                        if the dependency is supplied into dependent from outside, it is dependency injection.

                        interface EmployeeDAO{
                            void add(Employee e);
                            void delete(long empId);
                        }

                        class EmployeeDAOImpl implements EmployeeDAO {

                            //a few methods to talk to the database like
                            // void add(Employee emp)
                            // void delete(long empId)
                        }

                        class EmployeeDAOJPAImpl implements EmployeeDAO {

                            //a few methods to talk to the database like
                            // void add(Employee emp)
                            // void delete(long empId)
                        }

                        interface EmployeeService {

                        }
                                               
                        class EmployeeServiceImpl implements EmployeeService {
                            
                            private EmployeeDao empDao;

                            public EmployeeServiceImpl(EmployeeDao empDao){
                                this.empDao = empDao;
                            }
                        
                            public EmployeeServiceImpl(){
                        
                            }
                        
                            public void setEmpDao(EmployeeDao empDao){
                                this.empDao = empDao;
                            }
                            
                            //a few method to provide bussiness logic and call the dao methods when needed
                            // empDao.add(e)
                            // empDao.delete(id)
                        }

                        //Dependency Injection through Constructor - Constructor Injection
                        EmployeeService empService1 = new EmployeeServiceImpl(new EmployeeDaoImpl());
                        EmployeeService empService2 = new EmployeeServiceImpl(new EmployeeDaoJpaImpl());

                        //Dependency Injection through Setter - Setter Injection
                        EmployeeService empService3 = new EmployeeServiceImpl();
                        empService3.setEmpDao(new EmployeeDaoImpl());
            
            Spring Container

                CONTAINER   is the software who creates and manages objects of software components and does the DI.
                BEANS       are the Objects created by a CONTAINER 

                CONTAINER creates and manages BEANS of COMPONENTS.

                Spring has two containers
                    1. BeanFactory              Spring Beans Module
                    2. ApplicationContext       Spring Context Module

                Bean Configuration is the machanisim through which, the container is informed about the list of components and their dependencies.

                    1. XML Based Configuration
                    2. Annotation Based Configuration
                    3. Java Based Configuration

                Annotation Based Configuration

                    Step 1. Create a configuration class, provide the base package and provide the propertysource.

                        @Configuration
                        @ComponentScan("com.cts.hrapp")
                        @PropertySource("file location of .properties file")
                        public class MyBeanConfig {

                        }

                    Step 2. Mark the components

                        @Component
                            |-@Service
                            |-@Repository
                            |-@Controller
                            |-@RestController
                            ....etc.,

                    Step 3. Marh the dependencies

                        public interface EmployeeDAO{
                        
                        }

                        @Repository
                        public class EmployeeDAOImpl implements EmployeeDAO {

                        }                      

                        public interface EmployeeService {

                        }

                        @Service            
                        public class EmployeeServiceImpl implements EmployeeService {
                            
                            @Autowired
                            private EmployeeDao empDao;

                            @Value("${hra.percent}")
                            private double hraPercentage;
                                                       
                        }

                    Step 4: Create the container and load the configuration.

                        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);


                Java Based Configuration

                        @Configuration
                        @ComponentScan("com.cts.hrapp")
                        @PropertySource("file location of .properties file")
                        public class MyBeanConfig {

                            @Bean
                            public Scanner scan(){
                                return new Scanner(System.in);
                            }

                        }

                        @Component
                        public class EmployeeHomeScreen {
                            @Autowired
                            private Scanmner scan;    
                        }
                        
                        @Component
                        public class EmployeeAddScreen {

                            @Autowired
                            private Scanmner scan;    
                        }

                @Autowired

                    Autowiring is of five types
                        1. No               Spring Contaienr doesn't do any autowiring. if @Autowired is not used.
                        2. ByType           @Autowired
                        3. ByName           @Autowired + @Qualifier
                        4. Constructor      @Autowired on a constructor
                        5. Autodetect       Only while xml based config, Spring Container
                                             will try constructor autowiring and if fails will try byType.

                    Autowiring can be done in three ways
                        1. Field Injection          @Autowired on a field
                        2. Constructor Injection    @Autowired on a constructor
                        3. Setter Injection         @Autowired on a setter

                @Scope

                    1. singleton        it is the default, only one bean is created and injected whenever and whereever requested
                    2. prototype        a new bean is created for each injection
                    3. reqeust          works only with WebApplicationContext, a new bean is created for each HttpServletRequest 
                    4. session          works only with WebApplicationContext, a new bean is created for each HttpSession
                    5. global-session   works only with WebApplicationContext, a new bean is created for each ServletContext
        
    Spring Expression Language
    -------------------------------------------------

        https://www.baeldung.com/spring-expression-language

    Spring Boot
    --------------------------------------------------

        is a spring module that offers
            1. auto-configuration

                AutoConfiguration is about providing minimum boiler plate configs of any spring module like
                spring-context / spring-web / spring-data ...etc., spo that the app development can be
                done faster and a bit of debugging time can also be aliminated.

                AutoConfig attributes RAD - Rapid Application Development.

            2. embeded servers

                For enterprise applications like web-mvc apps or rest-apis ..etc., we need a web-server or
                an application-server to hos the app.

                Spring boot based app get a server embeded into app itself, making the app standalone. We
                do not need any external servers on which the app has to be hosted. This also makes the app
                container ready.
        
        Create a Spring Boot App (Spring Starter Project)        
            1. Using 'Spring Starter Project Wizard' of STS
            2. Using Spring Initlzr at 'https://start.spring.io'
            3. Using Spring Boot CLI

        @SpringBootApplication =  @Configuration + @ComponentScan + @EnableAutoConfig        

            As part of the Auto-config, appliction.properties is by default concedered as the properties file.

        SpringApplication.run(SpringBootDemoApplication.class, args);
            1. Create the bean container (ApplicationContext)
            2. The config is laoded into the bean container
            3. If any CommadnLineRunner classes exist, they are all executed in the chronological order
            4. If any embeded server exist, the server is started
            5. Waits until the server (if any) gets shut down
            6. The bean container is destroyed.

        interface CommandLineRunner
            void run(String args[])

    Spring Data 
    ------------------------------------------------

        Spring Data JDBC
            is a spring module that provide dynamic implementation to jdbc based  Repositories.

        Spring Data JPA
            is a spring module that provide dynamic implementation to jpa based  Repositories.

        CrudRepository
            |- JpaRepository<Entity,IdType>
                E save(E)
                Optional<E> findById(pk)
                void deleteById(pk)
                boolean existsById(pk)
                List<E> findAll()

        @Entity
        @Table(name="contacts")
        public class Contact {
            
            @Id
            private Long contactId;
            private String fullName;
            private String mobile;
            private String mailId;

            //constructors, setters and getters, hashvode and equals, toString
        }

        public interface ContactRepository extends JpaRepository<Contact,Long> {
            
            boolean existsByMailId(String mailId);
            Optional<Contact> findByMailId(String mailId);
            List<Contact> findAllByFullName(String fullName);

        }

        Spring Data Database Config

            in the .properties file we will have config., For MySQL
                spring.datasource.url = jdbc:mysql://localhost:3306/addb
                spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
                spring.datasource.username = root
                spring.datasource.password = root
                spring.jpa.show-sql = false
                spring.jpa.hibernate.ddl-auto = update
                spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

            in the .properties file we will have config., For h2Db
               spring.datasource.url=jdbc:h2:mem:testdb
               spring.datasource.driverClassName=org.h2.Driver
               spring.datasource.username=sa
               spring.datasource.password=password
               spring.jpa.show-sql = false
               spring.jpa.hibernate.ddl-auto = update    
               spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
                        
            in the .properties file we will have config., For h2Db
               spring.datasource.url=jdbc:h2:file:/data/demo
               spring.datasource.driverClassName=org.h2.Driver
               spring.datasource.username=sa
               spring.datasource.password=password
               spring.jpa.show-sql = false
               spring.jpa.hibernate.ddl-auto = update
               spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


    Spring Web
    --------------------------------------------------------

        is a spring framework module that supports web mvc applications and rest-api.

        Web Services
            a web service is a remote method (function) that is invoked through a request.
            are introduced to isolate the application/bussiness logic from the User Interfaces.

            PERSISTENCE TIER            APPLICATION TIER /                  UI                  CLIENT
                                          SERVICE TIER

                Database                   WEB-SERVICE                     UI/UX APP
                Server                  APPLICATION-SERVER/
                                            WEB-SERVER

                                                        <------------>  Angular APP
                                                        <------------>  IOS APP
                Database <--------->        webService  <------------>  Web APP
                                                        <------------>  Andriod APP
                                                        <------------>  ReactJS APP

            Types of Web Services
                SOAP        Simple Object Access Protocol
                            
                            Protocol Independent
                            Follow Messgeing System Based Archetecture
                            The only medium of commiunication is XML and is limited in capacity for modern apps.
                            Complex to develop, Heavy and Low Performing

                REST API    REpresentational State Transfer

                            Designed to work on HTTP protocol.
                            Leverage on the standered communication system of HTTP
                            Support XML, JSON, Binary and other types of medium of commucation because of being HTTP based.
                            Simple to develop, Light weight and compartivly or relativly fast in performing.

                GraphQL     Object Mapping and Querring based api

            REST API Standards

                1. Rest Api applications are resource centric.
                    Resource is any domain entity that has to be persisted. (for example, Employee, Student, Consumer ...etc.,)

                2. We create a single EndPoint for each resource,
                    Employee        /employees
                    Student         /students
                    Consumers       /consuemrs

                3. Each http-method is standardly mapped to each CRUD operation

                    CREATE          post
                    RETRIVE         get
                    UPDATE          put
                    DELETE          delete

                4. Each operation failing due to client side issues are responded with Http Status Code from 400 to 499

                    400     Bad Request
                    401     UnAuthorized
                    404     Not Found

                5. Each operation failing due to server side issues are responded with Http Status Code from 500 to 599

                    500     Internal Server Error
                    501     Not Implemented
                    502     Bad Gateway

                5. Each operation done successfully are responded with Http Status Code from 200 to 299

                    200     Ok      
                    201     Created
                    202     Accepted
                    204     No Content
                                                                                    Status Codes
        Operation   EndPoint    Http-Method Request Body    Resp Body   Success         Client-Side-Issue   Server-Side-Issue
        -----------------------------------------------------------------------------------------------------------------------

        Retrive     /emps       GET         NONE            emps[].json 200-OK          NONE                500-Internal Server Err
                    
                    /emps/101   GET         NONE            emp.json    200-OK          404-Not Found       500-Internal Server Err

        Insert      /emps       POST        emp.json        emp.json    201-Created     400-Bad Request     500-Internal Server Err

        Update      /emps       PUT         emp.json        emp.json    202-Accepted    400-Bad Request     500-Internal Server Err

        Delete      /emps/101   DELETE      NONE            NONE        204-No Content  404-Not Found       500-Internal Server Err

            
        Spring Web follows Single Front Controller Archetecture.

        Repo    <----models---->  Service <----model----> RestController(s) <---models---> FrontController  <------REQ--- Client
                                                            |
                                                            |-------------------------------------Response---------------->


        FrontController
            DispatcherServlet from Spring Framework works as front controller.

            1. Receive any reqeust sent to the server
            2. The request is then extracted for data if it carries any in the form of URL-Parameters / Query-Parameters / Request Body
            3. Then the data is passed to a action method in a rest-controller

        RestController
            is any class marked as @RestController.
            
            each rest-controller is mapped with a url using @RequestMapping("/endPoint")
            
            each rest-controller will have a set of mehtods called action methods that process a request.
            
            each action method is marked with @GetMapping / @PostMaping / @PutMapping / @DeleteMApping based on the operation the method does.

            each action method must return a ResponseEntity, where a ResponseEntity encapsulates the response body and
            the status code.

    Microservices
    ---------------------------------------------------------------------

        Structure of a Monolithical approach
            The entire application gets packed into one single deployment.
            The application is modularized for easy maintanence and tracking but still is very tightly coupled.

        Limitations of a Monolithical approach

            (-) Isolated Scaling is not possible.
            (-) Lack of Interoperability (technical upgradation is not easy)
            (-) The entire application is managed with a common thread pool limiting the enhancement of its load-capacity.

        Structure of Microservices

            This is an approach where a set of microservices each (individual and isolated) will
            share required information across but operate independently still being an integral
            part of an Eco-System.

        Advantages of Microservices

            (+) Isoalted scalling
            (+) Technical Upgradation or Interoperability
            (+) Individual microservices will have independent thread-pools

        Challenges In Microservices

            >> Decomposition
            >> Inter Service Communication
            >> Distributed Tracing
            >> Distributed Transactions

        Microservices Design Patterns

            (1) Decomposition by domain and sub-domain
            (2) Discovery Service Design Pattern
            (3) Load Balancer Design Pattern
            (4) Gateway Design Pattern

            (5) Circuite Breaker Design Pattern
            (6) Tracing Service Design Pattern
            (7) CQRS Design Pattern
            (8) External Configuration Design Pattern

        Decomposition by domain and sub-domain        

            1. An eco-system can identify its microservices based on the domain demanding modules.
            2. Identify the God Classes (God Classes are thsoe that are needed by multiple modules)
            3. Define the scope of those classes called bounded-context.
            4. Ensure the bounded-contexts are not shared.

            E-Commerce Eco-System (Application)
                
                Consumer Management Microservice
                    Consumer Entity
                        consuemrId
                        fullName
                        dateOfBirth
                        emailId
                        mobileNumber

                Sales Microservice
                    Consumer Entity
                        consuemrId
                        offersUsed
                        offersActive

                    Item Entity
                        itemId
                        cost
                        discount
                        tax

                Orders Microservice
                    Consumer Entity
                        consuemrId
                        orders: Set<Orders>

                    Order Entity
                        orderId
                        items: Set<Item>

                    Item Entity
                        itemId
                        qty
                        price

                Delivery Microservice
                    Consumer Entity
                        consumerId
                        address

                    Item Entity
                        itemId
                        isFragile

                Inventory Microservice
                    Item Entity
                        itemId
                        itemName
                        description
                        features
                        stock

        Discovery Service Design Pattern

            This addresses the challenge of inter-service-communication

            A discovery service is a microservice that manages a registery.
            
            Each time a new instance of a mciroservice starts, it will register it's address in this registry.
            
            If a microservice (say Ms-A) needs to request anotehr microservice (say Ms-B), then Ms-A gets the
            address of MS-B from the discovery-service.

            discovery-service
            (netflix eureka server)
                ↑↓
                ||
                ||
                ||=========================================
                        ↑↓              ↑↓              ↑↓
                    MicroService-A  MicroService-B  MicroService-C


        Load Balancer Design Pattern

            Ribbon , Spring Cloud Load Balancer are two of the most commonly used
            load balancers. These are included on each microservice to ensure that the
            requests are equally distributed among multiple instance of a microservice.

        Gateway Design Pattern

            discovery-service      <----------------->     api-gateway     <---------REQs----- CLIENT (SPA / rest client)
            (netflix eureka server)                     (spring cloud api gateway) or (zuul)
                ↑↓                                          ↑↓    
                ||                                          ||
                ||                                          ||
                ||==========================================||
                        ↑↓              ↑↓              ↑↓
                    MicroService-A  MicroService-B  MicroService-C

