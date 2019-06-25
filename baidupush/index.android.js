import { DeviceEventEmitter, NativeModules } from 'react-native';

const nativePush = NativeModules.RNReactNativeBaiduPushModule;

const DEVICE_NOTIF_EVENT = 'baiduNotificationReceived';
const NOTIF_REGISTER_EVENT = 'baiduNotificationsRegistered';
// const DEVICE_NOTIF_OPEN = '';

this.onReceiveMessageCB = null;
this.onRegisterCB = null;
export default {
    onRegister: (callback) => {
        this.onReceiveMessageCB = DeviceEventEmitter.addListener(NOTIF_REGISTER_EVENT, data => {
            let obj = {};
            obj.userId = data.userId;
            obj.channelId = data.channelId;
            callback(obj);
        });
    },
    onReceiveMessage: (callback) => {
        this.onReceiveMessageCB = DeviceEventEmitter.addListener(DEVICE_NOTIF_EVENT, data => {
            let obj = {};
            obj.title = data.title;
            obj.description = data.description;
            obj.extraData = JSON.parse(data.extraData);
            obj.foreground = data.foreground;
            obj.userInteraction = data.userInteraction;
            callback(obj);
        });
    },
    testPrint: (message) => {
        // eslint-disable-next-line no-console
        console.log('nativePush.testPrint', message);
        nativePush.testPrint(message);
    }
};
// class RNReactNativeBaiduPush {
//     constructor() {
//         this.onReceiveMessageCB = null;
//         this.onRegisterCB = null;
//     }
//     onRegister(callback) {
//         this.onReceiveMessageCB = DeviceEventEmitter.addListener(NOTIF_REGISTER_EVENT, data => {
//             let obj = {};
//             obj.userId = data.userId;
//             obj.channelId = data.channelId;
//             callBack(obj);
//         });
//     }
//     onReceiveMessage(callback) {
//         this.onReceiveMessageCB = DeviceEventEmitter.addListener(DEVICE_NOTIF_EVENT, data => {
//             let obj = {};
//             obj.title = data.title;
//             obj.description = data.description;
//             obj.extraData = JSON.parse(data.extraData);
//             obj.foreground = data.foreground;
//             obj.userInteraction = data.userInteraction;
//             callBack(obj);
//         });
//     }
//     testPrint(message) {
//         console.log('nativePush.testPrint', message);
//         nativePush.testPrint(message);
//     }
// }

// export default RNReactNativeBaiduPush;
