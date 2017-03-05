# AirfliteDeviceManager

## Access Database and retrieve the list of device
This function will access the developed database the retrieve the appropriate data. Currently the following data are displayable:
* ID: is being created automatically by the server
* Name: name of the device
* Model ID: user input
* Serial Number: user input
* Active date: is being recorded by the server

## Scan the RFID tag of a device
This function will retrieve the device information by its tag ID
```
if tagId match
    display the device info & allow user to edit or delete the device
else
    allow user to add new device with device data to be input
```
**Note:** _Device data to be input are **Name, Model ID, Serial Number**_

## Scan the barcode of a device
Under construction
