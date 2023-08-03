
CHANGES FOR PART C-J LISTED BELOW:

* Part C:
*Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.*

  * Changes in file src/main/resources/templates/mainscreen.html:
    * Title of page changed on line 14
    * Header showing title changed on line 19
    * Header for parts changed on line 23
    * Header for products changed on line 59<br><br>

* Part D: *Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.*
  * Changes in file src/main/resources/templates/mainscreen.html:
    * button added on line 20 linking to About Us page.
  * New file src/main/resources/templates/aboutUs.html detailing the store's info.
    <br><br>
* Part E: *Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.*
  * Products and parts were added to the database using the UI. As specified in application.properties, the save location URL is spring.datasource.url=jdbc:h2:file:./renamed-db-file-spring-boot-h2-db102

    <br><br>
* Part F: *Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:<br><br>
-- The “Buy Now” button must be next to the buttons that update and delete products.<br>
-- The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.<br>
-- Display a message that indicates the success or failure of a purchase.*<br><br>
  * Add file src/main/java/com/example/demo/controllers/BuyProductController.java
  * Add file src/main/resources/templates/buyNegativeError.html
  * Add file src/main/resources/templates/buySuccess.html
  * Modify file src/main/resources/templates/mainscreen.html
    * Button is added on line 91 in this file
      <br><br>
* Part G: *Modify the parts to track maximum and minimum inventory by doing the following:<br><br>
  --Add additional fields to the part entity for maximum and minimum inventory.<br>
  --Modify the sample inventory to include the maximum and minimum fields.<br>
  --Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.<br>
  --Rename the file the persistent storage is saved to.<br>
  --Modify the code to enforce that the inventory is between or at the minimum and maximum value.<br>*
  * Fields to track minimum and maximum inventory added in src/main/java/com/example/demo/domain/Part.java
    * Line numbers 36 and 37 in this file for the fields
    * Accessors and mutators on lines 70, 77, 81, 88
  * The inventory page shows these fields via changes to src/main/resources/templates/mainscreen.html
    * Lines 40 and 41 add headers
    * Lines 50 and 51 populate the parts with the field info
  * Input fields added to src/main/resources/templates/InhousePartForm.html
    * Lines 26-36 add the appropriate fields.
  * Input fields added to src/main/resources/templates/OutsourcedPartForm.html
    * Lines 27-37 add the appropriate fields.
  * Persistent storage save location is changed in src/main/resources/application.properties
    * Line number 6 changes the name
  * Enforcing the minimum and maximum values for inventory is acheived through validators.
    * The maximum limit is imposed by the new file src/main/java/com/example/demo/validators/EnufMaxPartsValidator.java
    * The minimum limit is imposed by the new file src/main/java/com/example/demo/validators/EnufMinPartsValidator.java
    * The error is displayed through these locations:
      * src/main/resources/templates/InhousePartForm.html line numbers 29, 35
      * src/main/resources/templates/OutsourcedPartForm.html line numbers 30, 36
        <br><br>
* Part H: *Add validation for between or at the maximum and minimum fields. The validation must include the following:<br><br>
  --Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.<br>
  --Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.<br>
  --Display error messages when adding and updating parts if the inventory is greater than the maximum.<br>*
  * The maximum limit is enforced when adding and updating <b>parts</b> by the new file src/main/java/com/example/demo/validators/EnufMaxPartsValidator.java
  * The minimum limit is enforced when adding and updating <b>parts</b> by the new file src/main/java/com/example/demo/validators/EnufMinPartsValidator.java
  * The error is displayed through these locations:
    * src/main/resources/templates/InhousePartForm.html line numbers 29, 35
    * src/main/resources/templates/OutsourcedPartForm.html line numbers 30, 36
  * The minimum limit is enforced when adding and updating <b>products</b> by the new file src/main/java/com/example/demo/validators/EnufMinPartsValidator.java
    * This is done by editing src/main/java/com/example/demo/validators/EnufPartsValidator.java
      * The changes are reflected in lines 29-71
      * Errors already appear for this validator in the base project, so no change was needed for that.
        <br><br>
* Part I: *Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.*
  * Four tests are added in src/test/java/com/example/demo/domain/PartTest.java lines 30-77
    <br><br>
* Part J: *Remove the class files for any unused validators in order to clean your code.*
  * No validators needed to be removed. The five that remain: DeletePartValidator, EnufMaxPartsvalidator, EnufMinPartsValidator, EnufPartsValidator and PriceProductValidator are all used currently.