/*
 * Copyright (c) 2015 by PROS, Inc.  All Rights Reserved.
 * This software is the confidential and proprietary information of
 * PROS, Inc. ("Confidential Information").
 * You may not disclose such Confidential Information, and may only
 * use such Confidential Information in accordance with the terms of
 * the license agreement you entered into with PROS.
 */

package com.pros.quotex.application.bootstrap.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.pros.quotex.application.bootstrap.model.constraint.BootstrapModelSdk;
import com.pros.quotex.interfaces.publicapi.quote.design.KeyboardShortcutKeyPublic;
import com.pros.quotex.interfaces.sdk.core.factory.SdkModelFactory;
import com.pros.quotex.interfaces.sdk.quote.design.ActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.Catalog;
import com.pros.quotex.interfaces.sdk.quote.design.ChunkedDataProviderActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.DataProviderActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.DataProviderWithInsertionDirectionActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.ListWidget;
import com.pros.quotex.interfaces.sdk.quote.design.Page;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteHeader;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteHeaderGroupFields;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteHeaderTab;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteHeaderTabs;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteModelElementTargetActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteModelSdk;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteModelStepSdk;
import com.pros.quotex.interfaces.sdk.quote.design.QuoteSubHeader;
import com.pros.quotex.interfaces.sdk.quote.design.RowTemplateActionSdk;
import com.pros.quotex.interfaces.sdk.quote.design.Toolbar;
import com.pros.quotex.interfaces.sdk.quote.design.ToolbarTab;
import com.pros.quotex.interfaces.sdk.quote.design.ToolbarTabContent;

/**
 * The model quote
 *
 * @author PROS - gmontes
 */
public class ModelQuote implements BootstrapModelSdk {

    public static final ModelDataProviders DATA_PROVIDER = new ModelDataProviders();
    // The define the model name
    private static final String MODEL_NAME = "SwethaDemo4";

    private final QuoteModelSdk model = SdkModelFactory.quoteModel(MODEL_NAME, new ModelSpreadsheet().getModel(),ModelSpreadsheet.SESSION_STATE_SALES_MANAGER,
    		ModelSpreadsheet.SESSION_STATE_SALES_ADMIN);

    // Actions
    private final List<ActionSdk> profileMenuDefaultActions = new ArrayList<>();
    private final List<ActionSdk> spreadsheetGridDefaultActions = new ArrayList<>();
    private final List<ActionSdk> searchAndAddDefaultActions = new ArrayList<>();
    private DataProviderActionSdk openCatalogAction;
    private DataProviderActionSdk openCatalogActionCatalog;
    private ChunkedDataProviderActionSdk closeAndSynchronizeAction;

    // the components id defined in Spreadsheet model
    private static final String SYNCHRO_SFDC_COMPONENT_ID = ModelSpreadsheet.SYNCHRO_SFDC_COMPONENT_ID;
    private static final String DISPLAY_CURRENCY_FIELD_COMPONENT_ID = ModelSpreadsheet.DISPLAY_CURRENCY_FIELD_COMPONENT_ID;

    private static final String GRID_COMPONENT_ID = ModelSpreadsheet.GRID_COMPONENT_ID;
    private static final String CUST_GRID_COMPONENT_ID = ModelSpreadsheet.CUST_GRID_COMPONENT_ID;
    private static final String FIELD_COMPONENT_ID = ModelSpreadsheet.FIELD_COMPONENT_ID;
    private static final String PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID = ModelSpreadsheet.PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID;
    private static final String PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID = ModelSpreadsheet.PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID;
    private static final String LEFT_BAR_ID = ModelSpreadsheet.LEFT_BAR_ID;
    private static final String PRODUCT_SELECTION = ModelSpreadsheet.PRODUCT_SELECTION;


    /**
     * Constructor.
     */
    public ModelQuote() {
        //Define global parameters for the quote
        //Lines will be inserted before the selected
        //Lines will be inserted at the top of the folder
        //Currencies used will be USD and EUR
        model.setDefaultLineActionDirectionBefore()
                .setDefaultFolderActionDirectionTop()
                .setSupportedCurrency("USD", "EUR")
                .setTranslation(Locale.FRANCE, "Swetha4 Demo Model")
                .setTranslation(Locale.US, "Swetha4 Demo Model");
        //Define the actions
        buildActions();
        //Define the Step 1
        buildStep1();
        //Define the Step 2
        buildStep2();
    }

    /**
     * Get the model
     * @return the model
     */
    public QuoteModelSdk getModel() {
        return model;
    }

    /**
     * Build the actions
     */
    private void buildActions() {

        //Define the Search&Add action
        DataProviderWithInsertionDirectionActionSdk searchProductCatalogAction = model.addAction.dataProviderWithInsertionDirection.searchProductsAction(DATA_PROVIDER.getCatalog());
        searchProductCatalogAction.setTranslation(Locale.FRANCE, "Chercher un produit")
                .setTranslation(Locale.US, "Search Product");



        //Add the Search&Add action
        searchAndAddDefaultActions.addAll(Arrays.asList(searchProductCatalogAction));


        //Define the open catalog action
        openCatalogAction = model.addAction.dataProvider.openCatalog(DATA_PROVIDER.getCatalog());
        openCatalogAction.setTranslation(Locale.FRANCE, "Ouvrir le catalogue")
                .setTranslation(Locale.US, "Open Catalog");
        
      //Define the open consumables catalog action
        openCatalogActionCatalog = model.addAction.dataProvider.openCatalog(DATA_PROVIDER.getConsumCatalog());
        openCatalogActionCatalog.setTranslation(Locale.FRANCE, "Ouvrir le catalogue")
                .setTranslation(Locale.US, "Open Consumables Catalog");

        //Define the close and synchronize action
        closeAndSynchronizeAction = model.addAction.chunkedDataProvider.closeAndSynchronizeAction(DATA_PROVIDER.getProviderSfdc(), SYNCHRO_SFDC_COMPONENT_ID);
        closeAndSynchronizeAction.setTranslation(Locale.FRANCE, "Synchroniser avec Salesforce")
                .setTranslation(Locale.US, "Synchronize With Salesforce");

        //Add actions on the profile menu
        profileMenuDefaultActions.addAll(Arrays.asList(model.systemAction.changeDisplayCurrency(),
                model.systemAction.changeDisplayLanguage()));
        
        RowTemplateActionSdk addSpecificLineAction = model.addAction.rowTemplate.addSpecificAction("SpecificLineTemplate");
        addSpecificLineAction.setTranslation(Locale.FRANCE, "Add unrelated product ")
                       .setTranslation(Locale.US, "Add unrelated product");
        
        model.setShortcut(KeyboardShortcutKeyPublic.DIGIT_1, addSpecificLineAction);

        //Define all actions we want to add on the grid
        spreadsheetGridDefaultActions.addAll(Arrays.asList(
                model.systemAction.copyRows(),
                model.systemAction.copyRowsToClipboard(),
                model.systemAction.cutRows(),
                model.systemAction.pasteRows(),
                model.systemAction.duplicateRows(),
                model.systemAction.deleteRows(),
                model.systemAction.copyCell(),
                model.systemAction.copyCellToClipboard(),
                model.systemAction.openArrange(),
                model.systemAction.openFilters(),
                model.systemAction.openSorts(),
                model.systemAction.compactDensity(),
                model.systemAction.normalDensity(),
                model.systemAction.tightDensity(),
                model.systemAction.openCurrencyRates(),
                model.systemAction.applyArrange(),
                model.systemAction.hideColumns(),
                model.systemAction.moveRows(),
                model.systemAction.pinColumns(),
                model.systemAction.applyFilters(),
                model.systemAction.applySorts(),
                addSpecificLineAction
        ));




    }

    /**
     * Builds the product step.
     */
    private void buildStep1() {
        // Create the step
        final QuoteModelStepSdk step = model.addStep()
                .setTranslation(Locale.FRANCE, "Product Selection")
                .setTranslation(Locale.US, "Product Selection");
        // Create the page
        final Page page = step.addPage()
                .setTranslation(Locale.FRANCE, "All")
                .setTranslation(Locale.US, "All");

        buildTopPageWidget(page);

        //Add a catalog widget
        final Catalog catalog = page.addPageCenter()
                .addCatalog("catUI_Food.xml")
                .setTranslation(Locale.FRANCE, "Catalogue")
                .setTranslation(Locale.US, "Catalog")
                .setActions(openCatalogAction);
        
     
      

        //Define this widget for display the search results page (from the Search&Add widget)
        final QuoteModelElementTargetActionSdk viewMoreLegacyCatalogFoodAction = model.addAction.quoteElementTarget.openCatalog(catalog);
        viewMoreLegacyCatalogFoodAction.setTranslation(Locale.FRANCE, "Voir plus")
                .setTranslation(Locale.US, "View more");
        
        searchAndAddDefaultActions.add(viewMoreLegacyCatalogFoodAction);
        
     // Create the page
        final Page Consumpage = step.addPage()
                .setTranslation(Locale.FRANCE, "Consumables")
                .setTranslation(Locale.US, "Consumables");
        
        buildTopPageWidget(Consumpage);
        
        
        final ListWidget mainList = Consumpage.addPageCenter().addHorizontalList();
        
      //Add a catalog widget
        mainList.addCatalog("catUI_Food.xml")
                 .setTranslation(Locale.FRANCE, "Catalogue")
                 .setTranslation(Locale.US, "Consumables Catalog")
                 .setActions(openCatalogActionCatalog);
        
        
      //Add a grid in the first element of the list
        mainList.addSpreadsheetGrid(CUST_GRID_COMPONENT_ID)
        .setTranslation(Locale.FRANCE, "Pricing Management - Tout")
        .setTranslation(Locale.US, "Pricing Management - All")
        //Add actions on the grid
        .setActions(spreadsheetGridDefaultActions)
        //Add an action bar
        .addActionBar()
        //Add the search&add widget
        .addSearchAndAdd(searchAndAddDefaultActions);

       
        
        

    }

    /**
     * Build the pricing step.
     */
    private void buildStep2() {
        // Create the Step
        final QuoteModelStepSdk step = model.addStep()
                .setTranslation(Locale.FRANCE, "Pricing")
                .setTranslation(Locale.US, "Pricing");

        // Create the Page
        final Page page = step.addPage()
                .setTranslation(Locale.FRANCE, "All")
                .setTranslation(Locale.US, "All");

        //Build the top widget
        buildTopPageWidget(page);

        //Build the left bar
        buildLeftWidget(page, LEFT_BAR_ID, PRODUCT_SELECTION);

        //Add a spreadsheet grid widget
        ListWidget mainList = page.addPageCenter().addHorizontalList();

        mainList.addSpreadsheetGrid(GRID_COMPONENT_ID)
                .setWeight(70)
                .setTranslation(Locale.FRANCE, "Pricing Management - Tout")
                .setTranslation(Locale.US, "Pricing Management - All")
                //Add actions on the grid
                .setActions(spreadsheetGridDefaultActions)
                //Add an action bar
                .addActionBar()
                //Add the search&add widget
                .addSearchAndAdd(searchAndAddDefaultActions);

        mainList.addSpreadsheetFields(FIELD_COMPONENT_ID)
                .setTranslation(Locale.FRANCE, "Totaux")
                .setTranslation(Locale.US, "Totals");
        //Define the footer
        page.addPageFooter();

    }

    /**
     * Add the top page widget common to every page
     * @param page the page
     */
    private void buildTopPageWidget(final Page page) {

        //Create the quote header
        final QuoteHeader quoteHeader = page.addQuoteHeader();
        //Add the top bar
        quoteHeader.addTopBar();
        //Add the profile menu
        quoteHeader.addProfileMenu(DISPLAY_CURRENCY_FIELD_COMPONENT_ID)
                .setActions(profileMenuDefaultActions);
        //Add the close button
        quoteHeader.addCloseAndSynchronize(SYNCHRO_SFDC_COMPONENT_ID)
                .setActions(Arrays.asList(closeAndSynchronizeAction));

        //Create the quote sub header
        final QuoteSubHeader quoteSubHeader = page.addQuoteSubHeader();
        //Add the quote title
        quoteSubHeader.addQuoteTitle();
        //Create tabs
        final QuoteHeaderTabs quoteHeaderTabs = quoteSubHeader.addQuoteHeaderTabs();
        //Create a new tab
        final QuoteHeaderTab quoteHeaderTab1 = quoteHeaderTabs.addQuoteHeaderTab(PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID);
        quoteHeaderTab1.setTranslation(Locale.FRANCE, "Informations du devis")
                .setTranslation(Locale.US, "Quote information");
        //Add fields in the tab
        final QuoteHeaderGroupFields quoteHeaderGroupFields1 = quoteHeaderTab1.addQuoteHeaderGroupFields();
        
       
        quoteHeaderGroupFields1.addQuoteHeaderFields(PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID)
        .setTranslation(Locale.FRANCE, "Consumables Total")
        .setTranslation(Locale.US, "Consumables Total");
        
        quoteHeaderGroupFields1.addQuoteHeaderFields(PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID)
                .setTranslation(Locale.FRANCE, "Informations du devis")
                .setTranslation(Locale.US, "Quote information"); 
        
        //Create the process bar
        page.addProcessBar();
        
        

    }
    
    /**
     * Add the top page widget common to Consumables
     * @param page the page
     */
    private void buildTopConsumPageWidget(final Page page) {

        //Create the quote header
        final QuoteHeader quoteHeader = page.addQuoteHeader();
        //Add the top bar
        quoteHeader.addTopBar();
        //Add the profile menu
        quoteHeader.addProfileMenu(DISPLAY_CURRENCY_FIELD_COMPONENT_ID)
                .setActions(profileMenuDefaultActions);
        //Add the close button
        quoteHeader.addCloseAndSynchronize(SYNCHRO_SFDC_COMPONENT_ID)
                .setActions(Arrays.asList(closeAndSynchronizeAction));

        //Create the quote sub header
        final QuoteSubHeader quoteSubHeader = page.addQuoteSubHeader();
        //Add the quote title
        quoteSubHeader.addQuoteTitle();
        //Create tabs
        final QuoteHeaderTabs quoteHeaderTabs = quoteSubHeader.addQuoteHeaderTabs();
        //Create a new tab
        final QuoteHeaderTab quoteHeaderTab1 = quoteHeaderTabs.addQuoteHeaderTab(PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID);
        quoteHeaderTab1.setTranslation(Locale.FRANCE, "Informations du devis")
                .setTranslation(Locale.US, "Quote information");
        //Add fields in the tab
        final QuoteHeaderGroupFields quoteHeaderGroupFields1 = quoteHeaderTab1.addQuoteHeaderGroupFields();
        
        
        quoteHeaderGroupFields1.addQuoteHeaderFields(PRICING_FIELDS_HEADER_TAB1_COMPONENT_ID)
                .setTranslation(Locale.FRANCE, "Informations du devis")
                .setTranslation(Locale.US, "Quote information"); 
        
        //Create the process bar
        page.addProcessBar();
        
        final QuoteHeaderTab quoteHeaderTab2 = quoteHeaderTabs.addQuoteHeaderTab(PRICING_FIELDS_HEADER_TAB2_COMPONENT_ID);
        quoteHeaderTab1.setTranslation(Locale.FRANCE, "Consumables Total")
                .setTranslation(Locale.US, "Consumables Total");
        

    }



    /**
     * Build the left widget common to every page
     * @param page the page
     * @param componentId the component id of the current page
     */
    private void buildLeftWidget(final Page page, final String componentId
    		, final String productId) {

        final Toolbar toolbarLeft = page.addLeftToolbar();

        final ToolbarTab toolbarLeftTab = toolbarLeft.addTab();
        toolbarLeftTab.setIcon("line")
                .setTranslation(Locale.FRANCE, "Ligne")
                .setTranslation(Locale.US, "Line")
                .addTitle(componentId)
                .setTranslation(Locale.FRANCE, "Détails de la ligne")
                .setTranslation(Locale.US, "Line details");
        final ToolbarTabContent tabContent = toolbarLeftTab.addContent();
        tabContent.addSection(componentId)
                .setTranslation(Locale.FRANCE, "Généralités")
                .setTranslation(Locale.US, "Generalities");
        tabContent.addSection(productId)
                .setTranslation(Locale.FRANCE, "Product Details")
                .setTranslation(Locale.US, "Product Details");

    }

}
