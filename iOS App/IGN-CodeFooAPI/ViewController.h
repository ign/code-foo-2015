//
//  ViewController.h
//  IGN-CodeFooAPI
//
//  Created by Stavropoulos Lab on 5/2/15.
//  Copyright (c) 2015 Kumaran. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>

@property (copy, nonatomic) NSArray *articles;
@property (copy, nonatomic) NSArray *videos;
@end
