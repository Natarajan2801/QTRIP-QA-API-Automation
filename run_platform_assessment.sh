#!/bin/bash

file="test-results.xml"

if [ -f "$file" ] ; then
    rm "$file"
fi

cp /tmp/external_build/reports/tests/test/testng-results.xml $file
sudo apt-get -y install python3-lxml
python3 assessment/xmlAssesment.py assessment/final_assessment_instructions.json $file