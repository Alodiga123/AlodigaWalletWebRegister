package com.portal.business.commons.startup;

import com.portal.business.commons.models.BPLanguage;

/**
 *
 * @author hvarona
 */
public class Languages {

    public static final BPLanguage[] LANG = new BPLanguage[]{
        new BPLanguage(1L, "ENGLISH", true, "en"),
        new BPLanguage(2L, "SPANISH", true, "es"),
        new BPLanguage(3L, "PORTUGUESE", true, "pt")};

}
