//
//  CustomCell.m
//  IGN-CodeFooAPI
//
//  Created by Stavropoulos Lab on 5/2/15.
//  Copyright (c) 2015 Kumaran. All rights reserved.
//

#import "CustomCell.h"

@implementation CustomCell {
    UILabel *_headlineValue;
    UILabel *_subHeadlineValue;
}

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        
        // Initialization code
        CGRect headlineLabelRectangle = CGRectMake(0, 5, 70, 25);
        UILabel *headlineLabel = [[UILabel alloc] initWithFrame:headlineLabelRectangle];
        headlineLabel.textAlignment = NSTextAlignmentRight;
        headlineLabel.text = @"";
        headlineLabel.font = [UIFont boldSystemFontOfSize:5];
        [self.contentView addSubview:headlineLabel];
        
        CGRect subHeadlineLabelRectangle = CGRectMake(0, 25, 70, 25);
        UILabel *subHeadlineLabel = [[UILabel alloc] initWithFrame:subHeadlineLabelRectangle];
        subHeadlineLabel.textAlignment = NSTextAlignmentRight;
        subHeadlineLabel.text = @"";
        subHeadlineLabel.font = [UIFont boldSystemFontOfSize:10];
        [self.contentView addSubview:subHeadlineLabel];
        
        CGRect headlineValueRectangle = CGRectMake(10, 5, 260, 35);
        _headlineValue = [[UILabel alloc] initWithFrame:headlineValueRectangle];
        _headlineValue.font = [UIFont boldSystemFontOfSize:10];
        _headlineValue.numberOfLines = 2;
        [self.contentView addSubview:_headlineValue];
        
        CGRect subHeadlineValueRectangle = CGRectMake(15, 35, 260, 35);
        _subHeadlineValue = [[UILabel alloc] initWithFrame:subHeadlineValueRectangle];
        _subHeadlineValue.font = [UIFont systemFontOfSize:10];
        _subHeadlineValue.numberOfLines = 2;
        [self.contentView addSubview:_subHeadlineValue];
    }
    
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (void)setHeadline:(NSString *)n {
    if(![n isEqualToString:_headline]) {
        _headline = [n copy];
        _headlineValue.text = _headline;
    }
}

- (void)setSubHeadline:(NSString *)w {
    if (![w isEqualToString:_subHeadline]) {
        _subHeadline = [w copy];
        _subHeadlineValue.text = _subHeadline;
    }
}

@end
