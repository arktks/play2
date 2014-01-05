#!/bin/sh

cd /home/tlab-app/play-2.2.1/apps
play -Dsbt.log.noformat=true clean compile stage & target/universal/stage/bin/apps -DapplyEvolutions.default=true &