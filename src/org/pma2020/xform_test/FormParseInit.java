package org.pma2020.xform_test;

import org.javarosa.core.model.FormDef;
import org.javarosa.xform.util.XFormUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * This class sets up everything you need to perform tests on the models and form elements found in JR (such
 * as QuestionDef, FormDef, Selections, etc).  It exposes hooks to the FormEntryController,FormEntryModel and
 * FormDef (all the toys you need to test IFormElements, provide answers to questions and test constraints, etc)
 * <p>
 * TODO Make some better lazy-people methods for testing constraints.
 * TODO Get the localizations tested so that test coverage isn't lost.
 * TODO Have a method to provide answers to test constraints.
 */
class FormParseInit {
    private static final Logger logger = LoggerFactory.getLogger(FormParseInit.class);
    private final String FORM_NAME;
    private FormDef xform;

    FormParseInit(Path form) {
        FORM_NAME = form.toString();
        this.init();
    }

    private void init() {
        String xf_name = FORM_NAME;
        FileInputStream is;
        try {
            is = new FileInputStream(xf_name);
        } catch (FileNotFoundException e) {
            logger.error("Error: the file '{}' could not be found!", xf_name);
            throw new RuntimeException("Error: the file '" + xf_name + "' could not be found!");
        }

        // Parse the form
        xform = XFormUtils.getFormFromInputStream(is);

        if (xform == null) {
            logger.error("ERROR: XForm has failed validation!!");
        }
    }

    /**
     * @return the FormDef for this form
     */
    FormDef getFormDef() {
        return xform;
    }
}
