//
//  AppDelegate.m
//  IGN CodeFoo
//
//  Created by Stavropoulos Lab on 4/30/15.
//  Copyright (c) 2015 Kumaran. All rights reserved.
//

#import "AppDelegate.h"
@interface AppDelegate ()<CLLocationManagerDelegate>
@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions

{
    self.locationManager = [[CLLocationManager alloc] init];
    if ([CLLocationManager locationServicesEnabled]) {
        [self.locationManager setDelegate:self];
        [self.locationManager startUpdatingLocation];
    }
    // Override point for customization after application launch.
    return YES;
}

#pragma mark - CLLocationManagerDelegate

- (void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus)status
{
    if (status == kCLAuthorizationStatusAuthorized) {
        [[NSNotificationCenter defaultCenter] postNotificationName:@"kCLAuthorizationStatusAuthorized" object:self];
    }
}

@end
