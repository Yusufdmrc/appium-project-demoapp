#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "                          📊 Opening Test Reports                          "
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Extent Report
EXTENT_REPORT="target/extent-reports/ExtentReport.html"
if [ -f "$EXTENT_REPORT" ]; then
    echo -e "${GREEN}✅ Opening Extent Report...${NC}"
    open "$EXTENT_REPORT"
    sleep 1
else
    echo -e "${YELLOW}⚠️  Extent Report not found${NC}"
fi

# Cucumber HTML Report (Masterthought)
CUCUMBER_REPORT="target/cucumber-reports/cucumber-html-reports/overview-features.html"
if [ -f "$CUCUMBER_REPORT" ]; then
    echo -e "${GREEN}✅ Opening Cucumber HTML Report...${NC}"
    open "$CUCUMBER_REPORT"
    sleep 1
else
    echo -e "${YELLOW}⚠️  Cucumber HTML Report not found${NC}"
fi

# Standard Cucumber Report
STANDARD_CUCUMBER="target/cucumber-reports/cucumber.html"
if [ -f "$STANDARD_CUCUMBER" ]; then
    echo -e "${GREEN}✅ Opening Standard Cucumber Report...${NC}"
    open "$STANDARD_CUCUMBER"
    sleep 1
else
    echo -e "${YELLOW}⚠️  Standard Cucumber Report not found${NC}"
fi

echo ""
echo -e "${BLUE}💡 TIP: To generate and open Allure report, run:${NC}"
echo -e "   ${YELLOW}mvn allure:serve${NC}"
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
