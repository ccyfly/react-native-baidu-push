
# react-native-my-baidu-push

## Getting started

`$ npm install react-native-my-baidu-push --save`

### Mostly automatic installation

`$ react-native link react-native-my-baidu-push`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.ccyfly.rnbaidupush.RNReactNativeBaiduPushPackage;` to the imports at the top of the file
  - Add `new RNReactNativeBaiduPushPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-my-baidu-push'
  	project(':react-native-my-baidu-push').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-my-baidu-push/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      implement project(':react-native-my-baidu-push')
  	```


## Usage
```javascript
import RNReactNativeBaiduPush from 'react-native-my-baidu-push';

// TODO: What to do with the module?
RNReactNativeBaiduPush;

```
  