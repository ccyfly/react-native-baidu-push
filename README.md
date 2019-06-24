
# react-native-react-native-baidu-push

## Getting started

`$ npm install react-native-react-native-baidu-push --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-baidu-push`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.ccyfly.rnbaidupush.RNReactNativeBaiduPushPackage;` to the imports at the top of the file
  - Add `new RNReactNativeBaiduPushPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-baidu-push'
  	project(':react-native-react-native-baidu-push').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-baidu-push/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-baidu-push')
  	```


## Usage
```javascript
import RNReactNativeBaiduPush from 'react-native-react-native-baidu-push';

// TODO: What to do with the module?
RNReactNativeBaiduPush;
```
  