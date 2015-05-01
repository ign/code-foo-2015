//
//  ViewController.m
//  IGN CodeFoo
//
//  Created by Stavropoulos Lab on 4/30/15.
//  Copyright (c) 2015 Kumaran. All rights reserved.
//

#import "MasterViewController.h"
#import "Group.h"
#import "ArtVidManager.h"
#import "ArticleVideo.h"
#import "DetailCell.h"

@interface MasterViewController () <ArtVidManagerDelegate> {
    NSArray *_groups;
    ArtVidManager *_manager;
}
@property (weak, nonatomic) CLLocationManager *locationManager;
@end

@implementation MasterViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    _manager = [[ArtVidManager alloc] init];
    _manager.communicator = [[ArticleVideo alloc] init];
    _manager.communicator.delegate = _manager;
    _manager.delegate = self;
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(startFetchingGroups:)
                                                 name:@"kCLAuthorizationStatusAuthorized"
                                               object:nil];
}

#pragma mark - Accessors

- (CLLocationManager *)locationManager
{
    if (_locationManager) {
        return _locationManager;
    }
    
    id appDelegate = [[UIApplication sharedApplication] delegate];
    if ([appDelegate respondsToSelector:@selector(locationManager)]) {
        _locationManager = [appDelegate performSelector:@selector(locationManager)];
    }
    return _locationManager;
}

#pragma mark - Notification Observer
- (void)startFetchingGroups:(NSNotification *)notification
{
    [_manager fetchGroupsAtCoordinate:self.locationManager.location.coordinate];
}

#pragma mark - ArtVidManagerDelegate
- (void)didReceiveGroups:(NSArray *)groups
{
    _groups = groups;
    [self.tableView reloadData];
}

- (void)fetchingGroupsFailedWithError:(NSError *)error
{
    NSLog(@"Error %@; %@", error, [error localizedDescription]);
}

#pragma mark - Table View
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _groups.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    DetailCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
    Group *group = _groups[indexPath.row];
    [cell.headlineLabel setText:group.headline];
    [cell.dataLabel setText:group.data];
    
    return cell;
}

@end
