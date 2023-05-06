Feature: To track shipment details of cargo flight

Background: 
Given Launch browser and load url and maximize screen

Scenario Outline: Track shipment details using Air Waybill numbers

And click Track Shipment button and the Track Shipment page will be loaded in seperate page
And 1st text box field of Air Waybill record should have 618 as default value and enter vaild waybill number in 2nd text box as <airwabillone>
When clicked Submit button and shipment details page will be opened 
And AWB number will be same as Air Waybill number entered on previous Track Shipment page
And click New Search button
And previous Track Shipment page should be opened and it will have Air waybill 1 number
And enter Air waybill 2 number as <airwabilltwo>
And enter Air waybill 3 number as <airwabillthree>
Then navigate to Shipment Details page
But Error message should be displayed
And close all tabs

Examples: 
|airwabillone|airwabilltwo|airwabillthree|
|'24160393'|'12345678'|'87654321'|
|'24160393'|'24160393'|'24160393'|
|'24160393'|'24160393'|'09997865'|