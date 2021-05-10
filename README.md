# Test Task for MacPaw 2021 Data Engineering Intern
The specifications of this task can be found [here](https://github.com/MacPaw/msi2021-data-engineering).

## Usage
In order to launch this project you need to have [Docker](https://www.docker.com/products/docker-desktop) and [scala-sbt](https://www.scala-sbt.org/) installed.

1.  Run `sbt docker:stage` to generate Dockerfile and compile the project.
2.  Run `docker-compose up` and wait until `flyway` service finishes the migration.
3.  Run `docker-compose down` and then `docker-compose up` to restart the `app` service.
