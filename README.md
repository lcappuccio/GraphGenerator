# GraphGenerator
A tool used to generate [DAG](https://en.wikipedia.org/wiki/Directed_acyclic_graph) graphs and output into csv file
in free form and symmetric (K-ary) form.

**Master**

[![Build Status](https://travis-ci.org/lcappuccio/graph-csv-generator.svg?branch=master)](https://travis-ci.org/lcappuccio/graph-csv-generator)
[![codecov.io](https://codecov.io/github/lcappuccio/graph-csv-generator/coverage.svg?branch=master)](https://codecov.io/github/lcappuccio/graph-csv-generator?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/02bf689d1c44466bab6411cdeca4429b)](https://www.codacy.com/app/leo_4/graph-csv-generator)

**Develop**

[![Build Status](https://travis-ci.org/lcappuccio/graph-csv-generator.svg?branch=develop)](https://travis-ci.org/lcappuccio/graph-csv-generator)
[![codecov.io](https://codecov.io/github/lcappuccio/graph-csv-generator/coverage.svg?branch=develop)](https://codecov.io/github/lcappuccio/graph-csv-generator?branch=develop)

## Usage
java -jar graphgenerator.jar -h

## Known Issues
At the moment no more than 10 child nodes per node are supported, if the limitation is removed you will get a checked
 exception.

## Extension
Can be integrated with [GraphDbPlayground](https://github.com/lcappuccio/GraphDbPlayground)