.PHONY: typical-sphinx-setup open-docs readme-to-docs \
build-docs-no-open build-docs docs-push-production docs-push-staging \
docs-push create-docs docs-create docs-build docs test-unit-tests \
test-files-to-static-doc-files push-docs push-docs-staging docs-open \
push-docs-production get-latest-jar run build-run run-only \
build jar xform-test xform-test-only test run test-only run-only

# TODO: Should be able to pass arguments.
# Test
test-unit-tests:
	python3 test/test.py
update-xml:
	xls2xform test/static/XformTest/input/XFormTest1.xlsx test/static/XformTest/output/XFormTest1.xml; \
	xls2xform test/static/NA/input/NA.xlsx test/static/NA/output/NA.xml
xform-test-only:
	java -jar build/libs/xform-test*.jar test/static/XformTest/output/XFormTest1.xml
xform-test:
	make update-xml; \
	make build; \
    make jar; \
    make xform-test-only
test: xform-test
test-only: xform-test-only
run: xform-test
run-only: xform-test-only

# Build
build:
	mv build/libs/* _dev/backup
	gradle build
jar:
	gradle jar; \
	cp build/libs/*.jar ../xform-test/xform_test/bin/*.jar
release: build install

# Install
make install:
	cp build/libs/xform-test*.jar /Library/Java/Extensions/xform-test.jar

# Docs
typical-sphinx-setup:
	sphinx-quickstart
open-docs:
	open docs/build/html/index.html
readme-to-docs:
	cp README.md docs/source/content/docs.md
test-files-to-static-doc-files:
	cp test/static/XformTest/input/XformTest1.xlsx docs/source/_static/xlsx_example.xlsx
build-docs-no-open:
	rm -rf docs/build/ && \
	make readme-to-docs && \
	make test-files-to-static-doc-files && \
	(cd docs && \
	make html)
build-docs:
	make build-docs-no-open && \
	make open-docs
docs-push-production:
	aws s3 sync docs/build/html s3://xform-test.pma2020.org --region us-west-2\
	 --profile work
docs-push-staging:
	aws s3 sync docs/build/html s3://xform-test-staging.pma2020.org --region\
	 us-west-2 --profile work
docs-push:
	make docs-push-staging && \
	make docs-push-production

create-docs: build-docs
docs-create: build-docs
docs-build: build-docs
docs: build-docs
docs-open: open-docs
push-docs: docs-push
push-docs-staging: docs-push-staging
push-docs-production: docs-push-production
