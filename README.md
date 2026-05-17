# SpringBoot_Microservice_Project

**Project: Spring Boot Microservices CI/CD Pipeline with Docker, Jenkins, AWS ECR and EKS**

Developed Spring Boot microservices including Service Registry, Config Server, API Gateway, Security Service, Department Service and User Service to support distributed business workflows.

Implemented Eureka Service Registry for service discovery, allowing microservices to register dynamically and communicate using service names instead of hardcoded URLs.

Configured Spring Cloud Config Server to manage centralized application configuration across multiple microservices and simplify environment-specific property management.

Developed API Gateway using Spring Cloud Gateway to route external client requests to backend services such as User Service, Department Service and Security Service.

Implemented JWT-based authentication through the Security Service and integrated token validation at the gateway layer to secure protected API endpoints.

Built RESTful APIs for User Service and Department Service, including user-to-department data retrieval by calling Department Service based on department ID.

Integrated Zipkin distributed tracing to track requests across API Gateway, User Service, Department Service and other microservices for better debugging and observability.

Created Dockerfiles for each Spring Boot microservice and containerized the applications to ensure consistent deployment across environments.

Created and managed AWS ECR repositories for storing Docker images of each microservice, including service-registry, config-server, security-service, department-service, user-service and api-gateway.

Built Docker images locally and through Jenkins, tagged them with AWS ECR repository URLs and pushed the images to Amazon Elastic Container Registry.

Created Kubernetes Deployment and Service YAML files for each microservice and deployed them into an Amazon EKS cluster.
Configured Kubernetes ClusterIP services for internal microservice communication and exposed the API Gateway externally using an AWS LoadBalancer service.

Installed and configured Jenkins on AWS EC2 and set up required tools including Java 21, Maven, Docker, AWS CLI, kubectl and eksctl.
Built an end-to-end Jenkins CI/CD pipeline to pull code from GitHub, build Maven artifacts, create Docker images, push images to AWS ECR and deploy services to EKS.

Configured Jenkins access to AWS and Kubernetes by setting up AWS credentials, kubeconfig and Docker permissions for the Jenkins user.

Troubleshot Jenkins pipeline failures related to Java version mismatch, Maven builds, Docker permissions, workspace issues and limited EC2 memory/storage.

Increased Jenkins EC2 storage and configured swap memory to improve pipeline stability during Maven and Docker build execution.

Managed EKS worker nodes, scaled node groups and resolved pod scheduling issues caused by limited memory, pod capacity and node readiness problems.

Monitored Kubernetes workloads using commands such as kubectl get pods, kubectl describe pod, kubectl logs, kubectl get svc and kubectl get nodes.

Troubleshot pod issues including Pending, Evicted, ContainerCreating, Terminating, NotReady nodes and service registration issues in Eureka.

Verified application deployment using Postman, tested gateway endpoints, passed JWT bearer tokens and validated service-to-service communication.

Used Eureka dashboard to confirm registered services and Zipkin UI to monitor distributed traces across the microservices.

Maintained project source code in GitHub and automated deployments through Jenkins pipeline execution whenever code changes were pushed.

Followed cloud-native deployment practices using Docker, Kubernetes, AWS EC2, AWS ECR, Amazon EKS, Jenkins and Spring Cloud technologies.
