# Library API Exercise

## Intro

The following assignment will test your knowledge of API design, as well as the MVC design pattern. 
We have taken the liberty of modeling the domain objects, as well as the service and repository layers of the application.
Starting the application will also launch an in-memory H2 database, loaded with a sample data set.

Please read the instructions carefully and completely before beginning the exercise.

## Background

You have been tasked to complete the library service to allow `CardHolder`'s to rent books from the library. 
Every `Book` can be assumed to be unique and the collection of books represents all books in the library. 
A `Rental` record is created when a book is available and is being requested by a `CardHolder` to be on loan. 

## Instructions
Write APIs to perform the following:

    - Rent a book to a card holder.
    - Get all books that are currently on loan.
    - Get all overdue books that are currently on loan.
    - Return a book.

- It is strongly recommended that you read through and familiarize yourself with the entire data model before starting. 
- You may choose to use the existing or create new controllers. 

We encourage you to show off your skills. Examples of ways you can do this are:

    - Write additional APIs you think might be useful.
    - Add testing
Be creative!

Please include any additional notes in `notes.md` including assumptions made, 
design decisions, additional libraries added or anything that you did that was beyond the asks.

## Submission
Please submit a single [patch file](https://git-scm.com/docs/git-format-patch) of your changes.

#### Documentation
For more information about Spring Boot and the related dependencies, please consult the `HELP.md` file.

#### Steps to Dockerize and deploying on Kubernetes running on Google Cloud

Step 1: Set current project in env variable 
#### CMD - gcloud config set project spring-boot-xxxx
Step 2: Create docker image using maven jib plugin and push to googel container regisrty $GOOGLE_CLOUD_PROJECT = spring-boot-xxxx
#### CMD - mvn com.google.cloud.tools:jib-maven-plugin:build -Dmaven.test.skip=true -Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/library-api
Step 3: Generate yml for deployment and services
#### Reference url - https://static.brandonpotter.com/kubernetes/DeploymentBuilder.html
Step 4: Login to gcloud inorder to work on kubectl commands which come builtin in GKE we can access through cloud shell to deploy in respective region
#### CMD - gcloud container clusters get-credentials standard-cluster-1 --zone us-central1-a
Step 5: Run kubectl command to apply deployment and service configurations
#### CMD - kubectl apply -f kube-library-api.yml

#### Cheat sheat for kubectl 
1. kubectl get pods
2. kubectl get deployment
3. kubectl get service
4. kubectl logs --follow pod_name



