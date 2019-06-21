/*
 * Copyright (c) 2015 by PROS, Inc.  All Rights Reserved.
 * This software is the confidential and proprietary information of
 * PROS, Inc. ("Confidential Information").
 * You may not disclose such Confidential Information, and may only
 * use such Confidential Information in accordance with the terms of
 * the license agreement you entered into with PROS.
 */

package com.pros.quotex.application.bootstrap.model;

import java.math.BigDecimal;
import java.util.Locale;

import com.pros.quotex.interfaces.publicapi.core.design.SessionStateIdentifierPublic;
import com.pros.quotex.interfaces.publicapi.spreadsheet.design.RowType;
import com.pros.quotex.interfaces.publicapi.spreadsheet.design.RuleMode;
import com.pros.quotex.interfaces.publicapi.spreadsheet.design.staticdomain.SortingCriteria;
import com.pros.quotex.interfaces.sdk.core.Catalog;
import com.pros.quotex.interfaces.sdk.core.factory.SdkModelFactory;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.Column;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.ColumnDefinition;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.Field;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.FieldDefinition;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SelectorType;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SpreadsheetModelProductRowTemplateSdk;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SpreadsheetModelRowTemplateCellSdk;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SpreadsheetModelSdk;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SpreadsheetModelSpecificRowTemplateSdk;
import com.pros.quotex.interfaces.sdk.spreadsheet.design.SpreadsheetModelViewSdk;

/**
 * The model spreadsheet
 *
 * @author PROS - gmontes
 */
public class ModelSpreadsheet {

    public static final SessionStateIdentifierPublic SESSION_STATE_SALES_MANAGER = SessionStateIdentifierPublic.fromString("SalesManager");
    
    public static final SessionStateIdentifierPublic SESSION_STATE_SALES_ADMIN = SessionStateIdentifierPublic.fromString("Administrator");
    // the spreadsheet model
    private final SpreadsheetModelSdk model = SdkModelFactory.spreadsheetModel(SESSION_STATE_SALES_MANAGER, SESSION_STATE_SALES_ADMIN);

    // the spreadsheet views identifiers
    /////////////////////////////////////
    public static final String VIEW_ID = "View";
    public static final String COLUMN_ID = "ColumnView";
    public static final String FIELD_VIEW_ID = "FieldView";
    public static final String SYNCHRO_VIEW_ID = "SynchronisationView";


    // the spreadsheet component identifiers
    /////////////////////////////////////////
    public static final String DISPLAY_CURRENCY_FIELD_COMPONENT_ID = "displayCurrenciesComponent";

    public static final String CATALOG_COMPONENT_ID = "catalogComponent";

    public static final String GRID_COMPONENT_ID = "gridComponent";
    public static final String CUST_GRID_COMPONENT_ID = "custGridComponent";
    public static final String LEFT_BAR_ID = "leftBar";
    public static final String FIELD_COMPONENT_ID = "fieldComponent";
    public static final String PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID = "pricingFieldsTab1Component";
    public static final String PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID = "pricingFieldsTab2Component";

    static final String SYNCHRO_SFDC_COMPONENT_ID = "SynchroSfdcComponentId";
    public static final String PRODUCT_SELECTION = "productSelection";


    // the variable identifiers

    /* Fields */
    private final Field total = model.addField.monetaryType("total", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Total").setTranslation(Locale.FRANCE, "Total")
            .setFormula("VSUM_ROOT(totalNetPrice)");

    private final Field displayCurrency = model.addField.currencyType("displayCurrency")
            .setTranslation(Locale.US, "Display currency").setTranslation(Locale.FRANCE, "Devise d'affichage")
            .setFormula("CURRENCY(\"USD\")");

    private final Field crmQuoteId = model.addField.stringType("CRM_QUOTE_ID")
            .setTranslation(Locale.US, "CRM Quote Identifier").setTranslation(Locale.FRANCE, "Identifiant Devis CRM");
    
    private final Field consumableTotal = model.addField.monetaryType("totalConsumable" ,model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Consumable Total").setTranslation(Locale.FRANCE, "Consumable Total")
            .setFormula("VSUM_IF(family=\"Consumable\",totalNetPrice)");
    
    /* End of Fields */

    /* Columns */
    private final Column currency = model.addColumn.currencyType("currency")
            .setTranslation(Locale.US, "Currency").setTranslation(Locale.FRANCE, "Currency");
    private final Column unitPrice = model.addColumn.monetaryType("unitPrice", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Base Price").setTranslation(Locale.FRANCE, "Prix catalogue");
    private final Column discount = model.addColumn.percentageType("discount")
            .setTranslation(Locale.US, "Discount").setTranslation(Locale.FRANCE, "Remise");
    private final Column netPrice = model.addColumn.monetaryType("netPrice", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Net Price").setTranslation(Locale.FRANCE, "Prix");
    private final Column totalNetPrice = model.addColumn.monetaryType("totalNetPrice", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Gross Revenue").setTranslation(Locale.FRANCE, "Revenu brut");
    private final Column family = model.addColumn.stringType("family").setTranslation(Locale.US, "Family").setTranslation(Locale.FRANCE, "Family");
    private final Column costPrice = model.addColumn.monetaryType("costPrice", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Cost").setTranslation(Locale.FRANCE, "Revenu brut");
    private final Column marginPrice = model.addColumn.monetaryType("marginPrice", currency)
            .setTranslation(Locale.US, "Margin").setTranslation(Locale.FRANCE, "Revenu brut");
    
    private final Column expert = model.addColumn.monetaryType("expert", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Expert").setTranslation(Locale.FRANCE, "Expert");
    
    private final Column target = model.addColumn.monetaryType("target", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Target").setTranslation(Locale.FRANCE, "Target");
    
    private final Column floor = model.addColumn.monetaryType("floor", model.systemField.referenceCurrency())
            .setTranslation(Locale.US, "Floor").setTranslation(Locale.FRANCE, "Floor");
    
    private final Column weight = model.addColumn.stringType("weight")
            .setTranslation(Locale.US, "Weight").setTranslation(Locale.FRANCE, "Weight");
    
    private final Column packageType = model.addColumn.stringType("packageType")
            .setTranslation(Locale.US, "Package Type").setTranslation(Locale.FRANCE, "packageType");
    
    private final Column caseWeight = model.addColumn.stringType("caseWeight")
            .setTranslation(Locale.US, "Case Weight").setTranslation(Locale.FRANCE, "caseWeight");
    
    /* End of Columns */

    private final FieldDefinition displayCurrencyFieldDefinition = model.addFieldDefinition(displayCurrency);
    private final FieldDefinition totalFieldDefinition = model.addFieldDefinition(total);


    //Cells
    private SpreadsheetModelRowTemplateCellSdk itemSheetCell;
    private SpreadsheetModelRowTemplateCellSdk itemQtyCell;
    private SpreadsheetModelRowTemplateCellSdk unitPriceCell;
    private SpreadsheetModelRowTemplateCellSdk discountCell;
    private SpreadsheetModelRowTemplateCellSdk netPriceCell;
    private SpreadsheetModelRowTemplateCellSdk totalNetPriceCell;
    private SpreadsheetModelRowTemplateCellSdk familyCell;
    private SpreadsheetModelRowTemplateCellSdk costCell;
    private SpreadsheetModelRowTemplateCellSdk marginCell;
    private SpreadsheetModelRowTemplateCellSdk unitEditableCell;
    private SpreadsheetModelRowTemplateCellSdk familyEditableCell;
    private SpreadsheetModelRowTemplateCellSdk currencyCell;
    private SpreadsheetModelRowTemplateCellSdk expertCell;
    private SpreadsheetModelRowTemplateCellSdk targetCell;
    private SpreadsheetModelRowTemplateCellSdk floorCell;
    private SpreadsheetModelRowTemplateCellSdk weightCell;
    private SpreadsheetModelRowTemplateCellSdk packageTypeCell;
    private SpreadsheetModelRowTemplateCellSdk caseWeightCell;

    /**
     * Constructor.
     */
    public ModelSpreadsheet() {

        // Set the root sub row policy
        model.rootLineTemplate.setLTType(RowType.FOLDER, RowType.PRODUCT, RowType.SPECIFIC);

        //Define system fields
        buildSystemFields();

        //Define the system columns
        buildSystemColumns();

        //Build the dataprovider context
        buildDataProviderContexts();

        //Define fields mapping
        buildFieldsMapping();

        //Define the line template resolution
        buildLineTemplateResolution();

        //Define the cells
        buildLTCells();

        //Define the line templates
        buildLineTemplates();

        //Build the views
        buildViews();

        // ARM
        handleARM(model);
    }

    /**
     * Get the model
     * @return the model
     */
    public SpreadsheetModelSdk getModel() {
        return model;
    }


    /**
     * Build the fields
     */
    private void buildSystemFields() {

        //Translate the system variable reference currency
        model.systemField.referenceCurrency().setTranslation(Locale.US, "Reference currency").setTranslation(Locale.FRANCE, "Monnaie de référence");
        //Translate the system variable creation date
        model.systemField.creationDate().setTranslation(Locale.US, "Date creation").setTranslation(Locale.FRANCE, "Date de création");

        //Define a domain for the display currency
        displayCurrency.domain.currencyDomain(SortingCriteria.ALPHABETICAL_ORDER).addCurrencyValue("EUR").addCurrencyValue("USD");
    }

    /**
     * Build the variables
     */
    private void buildSystemColumns() {

        //Translate the system column rowItem
        model.systemColumn.rowItem().setTranslation(Locale.US, "Name").setTranslation(Locale.FRANCE, "Description");
        //Translate the system column rowQuantity
        model.systemColumn.rowQuantity().setTranslation(Locale.US, "Quantity").setTranslation(Locale.FRANCE, "Quantité");

    }

    /**
     * Build Data provider context
     */
    private void buildDataProviderContexts() {

        //Define the context for the catalog dataprovider
        model.addDataProviderContext(ModelQuote.DATA_PROVIDER.getCatalog())
                .addInputMapping(model.systemField.referenceCurrency(), Catalog.CURRENCY)
                .addInputMapping(model.systemField.dataLocale(), Catalog.LOCALE);
        
      //Define the context for the consummable catalog dataprovider
        model.addDataProviderContext(ModelQuote.DATA_PROVIDER.getConsumCatalog())
                .addInputMapping(model.systemField.referenceCurrency(), Catalog.CURRENCY)
                .addInputMapping(model.systemField.dataLocale(), Catalog.LOCALE);

    }

    /**
     * Build external mapping for fields
     */
    private void buildFieldsMapping() {
        //Retrieve the Quote Id from the CRM thanks to the SFDC context
        model.addCellsDataProviderRule(ModelQuote.DATA_PROVIDER.getProviderSfdcContext(), "RetrieveSFDCContext", RuleMode.ONCE)
                .addOutputMapping(crmQuoteId, "CRM_QUOTE_ID");

    }

    /**
     * Build line template resolution mapping
     */
    private void buildLineTemplateResolution() {
        //Get variables needed for line template resolution
        model.addDataProviderRuleForTemplateResolution(ModelQuote.DATA_PROVIDER.getCatalog())
                .addInputMapping(model.systemColumn.rowItem(), "CPE.currentItem")
                .addOutputMapping(unitPrice, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[1].value")
                .addOutputMapping(costPrice, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[2].value")
                .addOutputMapping(expert, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[13].value")
                .addOutputMapping(target, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[14].value")
                .addOutputMapping(floor, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[15].value")
                .addOutputMapping(family, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpType.value")
                .addOutputMapping(weight, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpWeight.value")
                .addOutputMapping(packageType, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpPackage.value")
                .addOutputMapping(caseWeight, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpCaseWeight.value");
        
        model.addDataProviderRuleForTemplateResolution(ModelQuote.DATA_PROVIDER.getConsumCatalog())
        .addInputMapping(model.systemColumn.rowItem(), "CPE.currentItem")
        .addOutputMapping(unitPrice, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[1].value")
        .addOutputMapping(costPrice, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[2].value")
        .addOutputMapping(expert, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[13].value")
        .addOutputMapping(target, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[14].value")
        .addOutputMapping(floor, "CPE.currentItem.GET(CPE.Settings.Session.PricingMethod[1]).Range[15].value")
        .addOutputMapping(family, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpType.value")
        .addOutputMapping(weight, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpWeight.value")
        .addOutputMapping(packageType, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpPackage.value")
        .addOutputMapping(caseWeight, "CPE.currentItem.wksFood/BPS/bpsProperties.BP/bpCaseWeight.value");
    }

    /**
     * Build the line template cells
     */
    private void buildLTCells() {


        itemSheetCell = model.addModelRowTemplateCell("itemSheetCell", model.systemColumn.rowItem())
                .setSelector(SelectorType.BusinessDescriptionEdit).setCardRendering();

        itemQtyCell = model.addModelRowTemplateCell("itemQtyCell", model.systemColumn.rowQuantity())
                .setFormula("1")
                .setUserInput(true)
                .setSelector(SelectorType.BigDecimalTextField).setFormatter("BigDecimalFormatter").setMask("QuantityMask");

        unitPriceCell = model.addModelRowTemplateCell("unitPriceCell", unitPrice);

        netPriceCell = model.addModelRowTemplateCell("netPriceCell", netPrice)
                .setFormula("unitPrice")
                .setUserInput(true);
        
        netPriceCell.guidance(expert, target, floor)
                .setBaseValue(unitPrice);

        totalNetPriceCell = model.addModelRowTemplateCell("totalNetPriceCell", totalNetPrice)
                .setFormula("netPrice*"+model.systemColumn.rowQuantity().getId());

        discountCell = model.addModelRowTemplateCell("discountCell", discount)
                .setUserInput(true);

        discountCell.domain.percentageDomain(SortingCriteria.KEEP_DOMAIN_DEFINITION_ORDER).setMinimumValue(new BigDecimal(0)).setMaximumValue(new BigDecimal(100)).setMinIncluded(true).setMaxIncluded(true).setOpened(false);
        
        familyCell = model.addModelRowTemplateCell("familyCell", family);
        
        costCell = model.addModelRowTemplateCell("costCell", costPrice);
        
        marginCell = model.addModelRowTemplateCell("marginCell", marginPrice)
        		.setFormula("netPrice-costPrice");
        
        unitEditableCell = model.addModelRowTemplateCell("unitEditableCell", unitPrice)
        		.setUserInput(true);
        
        
        familyEditableCell = model.addModelRowTemplateCell("familyEditableCell", family)
        		.setUserInput(true)
        		.setSelector(SelectorType.DropDown);
        
        familyEditableCell.domain.textDomain(SortingCriteria.KEEP_DOMAIN_DEFINITION_ORDER)
        .addTextValue("Food").addTextValue("Consumable");
        
        currencyCell = model.addModelRowTemplateCell("currencyCell", currency)
        		.setFormula(model.systemField.referenceCurrency().getId())
        		.setUserInput(true);
        
        currencyCell.domain.currencyDomain(SortingCriteria.REVERSE_ALPHABETICAL_ORDER)
        .addCurrencyValue("EUR").addCurrencyValue("USD");
        
        expertCell = model.addModelRowTemplateCell("expertCell", expert);
        
        targetCell = model.addModelRowTemplateCell("targetCell", target);
        
        floorCell = model.addModelRowTemplateCell("floorCell", floor);
        
        weightCell = model.addModelRowTemplateCell("weightCell", weight);
        
        packageTypeCell = model.addModelRowTemplateCell("packageTypeCell", packageType);
        
        caseWeightCell = model.addModelRowTemplateCell("caseWeightCell", caseWeight);

    }

    /**
     * Build the line templates
     */
    private void buildLineTemplates() {

    	//build line template with more restrictive
    	final SpreadsheetModelProductRowTemplateSdk consumableTemplate = model.addProductRowTemplate("consumableLineTemplate")
    			.addModelCell(itemSheetCell, itemQtyCell, currencyCell, unitPriceCell, netPriceCell, discountCell,totalNetPriceCell,
    					familyCell, costCell, marginCell, expertCell, targetCell, floorCell, weightCell
    					, packageTypeCell, caseWeightCell);
    	consumableTemplate.setMatchingRule.eqRestriction(family).setSimpleValue.stringValue("Consumable");
        //build the product line template
        final SpreadsheetModelProductRowTemplateSdk productLineTemplate = model.addProductRowTemplate("ProductLineTemplate");
        //Add cells on the line template
        productLineTemplate.addModelCell(itemSheetCell, itemQtyCell, currencyCell, unitPriceCell, netPriceCell, discountCell,
        		totalNetPriceCell,familyCell, expertCell, targetCell, floorCell, weightCell
				, packageTypeCell, caseWeightCell);
        
        final SpreadsheetModelSpecificRowTemplateSdk specificLineTemplate = model.addSpecificRowTemplate("SpecificLineTemplate")
    			.addModelCell(itemSheetCell, itemQtyCell, currencyCell, unitEditableCell, familyEditableCell );
    }

    /**
     * Build the views
     */
    private void buildViews() {

        buildView();
        buildFamilyView();
        buildFieldView();
        buildSynchroView();
    }

    /**
     * Build the view VIEW_ID
     */
    private void buildView() {
    	//Create a view
        final SpreadsheetModelViewSdk view = model.addModelView(VIEW_ID);

        //Add a component
        ColumnDefinition gridColumnDefinition = model.addColumnDefinition(
                model.systemColumn.rowItem(), model.systemColumn.rowQuantity(), unitPrice, currency, discount, netPrice,costPrice,marginPrice, totalNetPrice,family);
        model.addGridData(GRID_COMPONENT_ID, view, gridColumnDefinition)
                .setDefaultWidth(120)
                .setDocked(model.systemColumn.rowItem())
                .setWidth(300, model.systemColumn.rowItem());

        //Add a component
        ColumnDefinition leftBarColumnDefinition = model.addColumnDefinition(
                model.systemColumn.rowItem(), model.systemColumn.rowQuantity(), netPrice, totalNetPrice);
        model.addGridData(LEFT_BAR_ID, view, leftBarColumnDefinition);
        
        ColumnDefinition productColumnDefinition = model.addColumnDefinition(
                weight, packageType, caseWeight);
        model.addGridData(PRODUCT_SELECTION, view, productColumnDefinition);
    }
    
    /**
     * Build the view VIEW_ID
     */
    private void buildFamilyView() {
    	//Create a view
        final SpreadsheetModelViewSdk ConsumView = model.addModelView("ConsumableView");
        ConsumView.setRowsFilter.eqRestriction(family).setSimpleValue.stringValue("Consumable");

        //Add a component
        ColumnDefinition gridColumnDefinition = model.addColumnDefinition(
                model.systemColumn.rowItem(), model.systemColumn.rowQuantity(), unitPrice);
        model.addGridData(CUST_GRID_COMPONENT_ID, ConsumView, gridColumnDefinition)
                .setDefaultWidth(120)
                .setDocked(model.systemColumn.rowItem())
                .setWidth(300, model.systemColumn.rowItem());
    
    }

    /**
     * Build the view FIELD_VIEW_ID
     */
    private void buildFieldView() {
    	//Create a view
        final SpreadsheetModelViewSdk view = model.addModelView(FIELD_VIEW_ID);
      //Add a component
        model.addFieldData(DISPLAY_CURRENCY_FIELD_COMPONENT_ID, view, displayCurrencyFieldDefinition);

        //Add a component
        model.addFieldData(FIELD_COMPONENT_ID, view, totalFieldDefinition);

        model.addFieldData(PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID, view, totalFieldDefinition);
        
        final FieldDefinition fieldDefPricingTab2 = model.addFieldDefinition(consumableTotal);
        //Add a component for the header tab
        model.addFieldData(PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID, view, fieldDefPricingTab2);
    }

    /**
     * Build the synchro view
     */
    private void buildSynchroView(){

        //Define the synchronization view
        final SpreadsheetModelViewSdk view = model.addModelView(SYNCHRO_VIEW_ID);
        ColumnDefinition columnDefinition = model.addColumnDefinition(
                model.systemColumn.rowItem(),
                model.systemColumn.rowQuantity(),
                unitPrice,
                currency,
                discount,
                netPrice,
                totalNetPrice);
        FieldDefinition fieldDefinition = model.addFieldDefinition(
                crmQuoteId,
                total);
        model.addColumnFieldData(SYNCHRO_SFDC_COMPONENT_ID, view, columnDefinition, fieldDefinition);
    }

    /**
     * Handle the Access Rights
     * @param model the spredasheet model
     */
	private void handleARM(final SpreadsheetModelSdk model) {
		model.addSessionState(SESSION_STATE_SALES_ADMIN.getName());
        //model.addSessionState(SESSION_STATE_SALES_MANAGER.getName())
        //.setFilter
       // .setRestriction(ConsumView.setHierarchicalFilter.isRowAddedRestriction());
	}
}
