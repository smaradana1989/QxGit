/*
 * Copyright (c) 2015 by PROS, Inc.  All Rights Reserved.
 * This software is the confidential and proprietary information of
 * PROS, Inc. ("Confidential Information").
 * You may not disclose such Confidential Information, and may only
 * use such Confidential Information in accordance with the terms of
 * the license agreement you entered into with PROS.
 */

package com.pros.quotex.application.bootstrap.model;

import com.pros.quotex.interfaces.sdk.core.factory.SdkModelFactory;
import com.pros.quotex.interfaces.sdk.dataprovider.design.CatalogDataProviderSdk;
import com.pros.quotex.interfaces.sdk.dataprovider.design.ContextDataProviderSdk;
import com.pros.quotex.interfaces.sdk.dataprovider.design.SfdcDataProviderSdk;

/**
 * The model data providers
 *
 * @author PROS - gmontes
 */
public class ModelDataProviders {

    /**
     * Catalog legacy DataProviders
     */
    private final CatalogDataProviderSdk catalog = SdkModelFactory.catalog("catalog");
    private final CatalogDataProviderSdk catalogConsum = SdkModelFactory.catalog("catalogConsumables");

    /**
     *  Salesforce Data provider
     */
    private final SfdcDataProviderSdk providerSfdc = SdkModelFactory.sfdc("Sfdc");
    private final ContextDataProviderSdk providerSfdcContext = SdkModelFactory.context("SfdcContext");

    /**
     * Constructor
     */
    public ModelDataProviders() {
        catalog.addSessionSettings("CPE.Settings.Session.ModelVersion", "WORKING");
        catalog.addSessionSettings("CPE.Settings.Session.PricingMethod[1]", "PRGM/pmDefault");
        catalog.addSessionSettings("CPE.Settings.Session.SalesMethodName", "smDefault");
        catalog.addSessionSettings("CPE.Settings.Session.IntegerFormat", "#,##0");
        catalog.addSessionSettings("CPE.Settings.Session.LongDateFormat", "YYYY_MM_dd DDD HH#mm#ss.s");
        catalog.addSessionSettings("CPE.Settings.Session.MonetaryFormat", "¤ #,##0.00;(¤#,##0.00)");
        catalog.addSessionSettings("CPE.Settings.Session.NumericFormat", "#,##0.###");
        catalog.addSessionSettings("CPE.Settings.Session.ShortDateFormat", "MM/dd/yyyy");
        catalog.addSessionSettings("CPE.Settings.Session.TimeZone", "GMT");
		catalog.addSessionSettings("CPE.Settings.Session.rootCL","wksFood/CL/catFoods");
		
		catalogConsum.addSessionSettings("CPE.Settings.Session.ModelVersion", "WORKING");
		catalogConsum.addSessionSettings("CPE.Settings.Session.PricingMethod[1]", "PRGM/pmDefault");
		catalogConsum.addSessionSettings("CPE.Settings.Session.SalesMethodName", "smDefault");
		catalogConsum.addSessionSettings("CPE.Settings.Session.IntegerFormat", "#,##0");
		catalogConsum.addSessionSettings("CPE.Settings.Session.LongDateFormat", "YYYY_MM_dd DDD HH#mm#ss.s");
		catalogConsum.addSessionSettings("CPE.Settings.Session.MonetaryFormat", "¤ #,##0.00;(¤#,##0.00)");
		catalogConsum.addSessionSettings("CPE.Settings.Session.NumericFormat", "#,##0.###");
		catalogConsum.addSessionSettings("CPE.Settings.Session.ShortDateFormat", "MM/dd/yyyy");
		catalogConsum.addSessionSettings("CPE.Settings.Session.TimeZone", "GMT");
		catalogConsum.addSessionSettings("CPE.Settings.Session.rootCL","wksFood/CL/catDedicatedConsum");
		//catalog.addSessionSettings("CPE.Settings.Session.rootCL","wksFood/CL/catDedicatedConsum");
    }

    /**
     * Get the full catalog provider
     * @return fullCatalog provider
     */
    public CatalogDataProviderSdk getCatalog() {
        return catalog;
    }
    
    /**
     * Get the full catalog provider
     * @return fullCatalog provider
     */
    public CatalogDataProviderSdk getConsumCatalog() {
        return catalogConsum;
    }
    

    /**
     * Get the SFDC provider
     * @return sfdc provider
     */
    public SfdcDataProviderSdk getProviderSfdc() {
        return providerSfdc;
    }

    /**
     * Get the context provider
     * @return context provider
     */
    public ContextDataProviderSdk getProviderSfdcContext() {
        return providerSfdcContext;
    }

}