import { NativeModules } from 'react-native';

const { RNReactNativeBaiduPush } = NativeModules;

const DEVICE_NOTIF_EVENT = 'baiduNotificationReceived';
const NOTIF_REGISTER_EVENT = 'baiduNotificationsRegistered';
const DEVICE_NOTIF_OPEN = '';

class RNReactNativeBaiduPush {
    constructor() {
        this.onReceiveMessageCB = null;
        this.onRegisterCB = null;
    }
    onRegister(callback) {
        this.onReceiveMessageCB = DeviceEventEmitter.addListener(NOTIF_REGISTER_EVENT, data => {
            let obj = {};
            obj.userId = data.userId;
            obj.channelId = data.channelId;
            callBack(obj);
        });
    }
    onReceiveMessage(callback) {
        this.onReceiveMessageCB = DeviceEventEmitter.addListener(DEVICE_NOTIF_EVENT, data => {
            let obj = {};
            obj.title = data.title;
            obj.description = data.description;
            obj.customContentString = JSON.parse(data.customContentString);
            callBack(obj);
        });
    }
}

export default RNReactNativeBaiduPush;
