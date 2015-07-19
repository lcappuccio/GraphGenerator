# GraphGenerator
A tool used to generate [DAG](https://en.wikipedia.org/wiki/Directed_acyclic_graph) graphs and output into csv file
in free form and symmetric (K-ary) form.

[![Build Status](https://travis-ci.org/lcappuccio/GraphGenerator.svg?branch=master)](https://travis-ci.org/lcappuccio/GraphGenerator)

## Usage
java -jar graphgenerator.jar -h

## Known Issues
At the moment no more than 10 child nodes per node are supported, if the limitation is removed you will get a checked
 exception.

## Extension
Can be integrated with [OrientPlayGround](https://github.com/lcappuccio/OrientPlayGround)