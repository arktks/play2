#!/bin/sh

source constants.sh

cd $app_home
play -Dsbt.log.noformat=true clean compile stage & target/universal/stage/bin/apps -DapplyEvolutions.default=true &  