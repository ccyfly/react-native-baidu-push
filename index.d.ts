export interface IBaiduRegisteredInfo {
    userId: string;
    channelId: string;
};
export interface IBaiduNotification {
    title: string;
    message: string;
    extraData: string;
    foreground: boolean;
    userInteraction: boolean;
}

declare const module: {
    onRegister: (cb: (data: IBaiduRegisteredInfo) => void) => void;
    onReceiveMessage: (cb: (data: IBaiduNotification) => void) => void;
    testPrint: (msg: string) => void;
};
export default module;