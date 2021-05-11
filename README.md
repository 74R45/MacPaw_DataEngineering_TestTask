# Test Task for MacPaw 2021 Data Engineering Intern
The specifications of this task can be found [here](https://github.com/MacPaw/msi2021-data-engineering).
For this task the preferred language was Python, and I am quite knowledgeable about it (having completed a course in it back in 2016), yet I decided to use Scala because that's the language I'm most comfortable with and therefore can write the most impressive code in.

## Usage
In order to launch this project you need to have [Docker](https://www.docker.com/products/docker-desktop) and [scala-sbt](https://www.scala-sbt.org/) installed.

1.  Run `sbt docker:stage` to generate Dockerfile and compile the project.
2.  Run `docker-compose up` and wait until `flyway` service finishes the migration.
3.  Run `docker-compose down` and then `docker-compose up` to restart the `app` service.
