//
//  ViewController.m
//  IGN-CodeFooAPI
//
//  Created by Stavropoulos Lab on 5/2/15.
//  Copyright (c) 2015 Kumaran. All rights reserved.
//

#import "ViewController.h"
#import "CustomCell.h"


@interface ViewController ()

@end

@implementation ViewController

static NSString *CellIdentifier = @"CellTableIdentifier";


- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    
    _articles = @[
                  @{@"Headline": @"01 - Shadows of the Empire's Outrider Confirmed as Star Wars Canon", @"subHeadline": @"The power of a good cameo."},
                  @{@"Headline": @"02 - Grand Theft Auto 5: Shoot Car Missiles From Your Car With Mayhem Mod", @"subHeadline": @"Invincibility highly recommended."},
                  @{@"Headline": @"03 - Why Suikoden 2 Is the Best Game You've Never Played", @"subHeadline": @""},
                  @{@"Headline": @"04 - New Release Date Set for Live-Action Ghost in the Shell Movie", @"subHeadline": @"Starring The Avengers' Black Widow, Scarlett Johansson."},
                  @{@"Headline": @"05 - Suicide Squad: Set Photos Surface", @"subHeadline": @"And is that Batfleck on set?!"},
                  @{@"Headline": @"06 - Channing Tatum Attached to Sci-Fi Adaptation The Forever War", @"subHeadline": @"Prometheus writer Jon Spaihts is attached to pen the screenplay."},
                  @{@"Headline": @"07 - Here's When You Can See Ouija 2", @"subHeadline": @"Michael Bay is back to produce the horror sequel."},
                  @{@"Headline": @"08 - New Terminator Genisys Poster Revealed", @"subHeadline": @"Jason Clarke is on fire these days!"},
                  @{@"Headline": @"09 - Marvel's Avengers: Age of Ultron Review", @"subHeadline": @"Robocalypse"},
                  @{@"Headline": @"10 - Mountains of Mouthness Will Read Your Tweets Out Loud", @"subHeadline": @"Tweet it from the mountaintops."},
                  @{@"Headline": @"11 - Xbox 360 Update Adds 2TB Hard Drive Support", @"subHeadline": @"As well as accessible password reset and purchase history."},
                  @{@"Headline": @"12 - New Fantastic Four Character Posters", @"subHeadline": @"It's clobberin' time."},
                  @{@"Headline": @"13 - Will Windows 10 Unlock Achievements for Everything?", @"subHeadline": @"'Read This Article' unlocked!"},
                  @{@"Headline": @"14 - Silent Hills Petition On Change.org Begs Kojima To Continue Development", @"subHeadline": @"There was a game here. It's gone now."},
                  @{@"Headline": @"15 - Developers Can Now Ban Players for Cheating on Steam", @"subHeadline": @"'Playing games should be fun.'"},
                  @{@"Headline": @"16 - Microsoft Facial Recognition Software Can Guess Your Age", @"subHeadline": @"Somewhere between 0-150?"},
                  @{@"Headline": @"17 - App Store Update: April 30", @"subHeadline": @"Step into the visually simple but strategically complex world of Battledots, then score Mighty Switch Force! Hose It Down! totally for free."},
                  @{@"Headline": @"18 - Is Microsoft HoloLens Ready for Prime Time?", @"subHeadline": @"Will AR come before VR more than just alphabetically?"},
                  @{@"Headline": @"19 - Daily Deals: Mario Party 10, KOTOR on iOS, Xbox Live for $32", @"subHeadline": @"Great deals on consoles, games, and gaming PCs."},
                  @{@"Headline": @"20 - Power Rangers Movie Release Date Pushed to 2017", @"subHeadline": @"Now slated for January 13, 2017."},
                  ];
    
    _videos = @[
                @{@"Head": @"01 - Mad Max: Fury Road Heads Up May's Must See Movies                                                                1:28", @"subHead": @"The big releases feature earthquakes, Mi5 agents, a President in mortal danger, and the return of an iconic celluloid character."},
                @{@"Head": @"02 - Block N Load: Paintball og intervju med Jagex     6:07", @"subHead": @"Vi fikk sjansen til å prøve sandbox-shooteren Block N Load fra Jagex, først i form av en frenetisk painball-runde basert på spillets regler, og deretter på PC. I tillegg tok vi en prat med visepresident David Solari."},
                @{@"Head": @"03 - Is Jurassic World Forgetting its Horror Roots? - Fresh Meat          13:27", @"subHead": @"The original Jurassic Park was packed with horror. Will Jurassic World deliver similar scares?"},
                @{@"Head": @"04 - IGN PT Podcast #6 - A Epídemia do Dragão          58:54", @"subHead": @"A equipa que constitui o Podcast volta a ser reduzida a dois neste 6º/7º Episódio, onde o André e o Pedro falam das melhores notícias da semana, e que semana..."},
                @{@"Head": @"05 - Monster Hunter 4 Ultimate - I DLC di maggio          2:19", @"subHead": @"Quest inedite, costumi, fra cui quello dedicato a Samus Aran, e molto altro. Il mese sembra davvero ricco!"},
                @{@"Head": @"06 - Frozen Cortex - L'allenatore tecnologico          6:47", @"subHead": @"L'esperimento Mode 7, con questo aggiornamento gratuito, si siede in panchina, dettando tempi e tattiche."},
                @{@"Head": @"07 - What's Up IGN - Call of Duty: Black Ops 3          4:53", @"subHead": @"We moeten het uiteraard even hebben over de onthulling van Call of Duty: Black Ops 3 en doen dat in een What's Up IGN."},
                @{@"Head": @"08 - IGN Portugal - Lançamentos de maio          6:28", @"subHead": @"O mês que antecede a E3 traz-nos jogos para todos os gostos, com alguns lançamentos há muito esperados."},
                @{@"Head": @"09 - Square Enix - Una tech demo in DirectX 12     4:02", @"subHead": @"Le immagini, davvero spettacolari, ci arrivano dalla Build di San Francisco. Il cuore della demo è un super PC, dotato di quattro GPU GeForce Titan X."},
                @{@"Head": @"10 - Persona 4: Dancing All Night - Nanako Dojima     1:31", @"subHead": @"Un marcato senso del ritmo, tanta energia e quel pizzico d'incoscienza, legato alla sua giovane età. Nanako è tutto questo."},
                @{@"Head": @"11 - JoJo’s Bizarre Adventure: Eyes of Heaven - Inizia lo scontro     2:20", @"subHead": @"Una sequenza di combattimento, spettacolare e un po' confusionaria, tratta dal picchiaduro Bandai Namco."},
                @{@"Head": @"12 - Spectre: il video backstage di Roma     1:41", @"subHead": @"Uno spettacolare inseguimento automobilistico per le strade di Roma fa da sfondo a un breve dietro le quinte del nuovo film di 007 in uscita a novembre."},
                @{@"Head": @"2:08   ה-FIX השבועי של IGN ל-1.5.15     13 ", @"subHead": @"כל החדשות הכי חמות של השבוע במקום אחד."},
                @{@"Head": @"14 - Mad Max: Furia en la carretera - Tráiler final     2:23", @"subHead": @"Los ochenta vuelven con la brutal película de Mad Max."},
                @{@"Head": @"15 - Un video per l'ultimo aggiornamento di Space Engineers     3:08", @"subHead": @"Un nuovo video del gioco che mostra le novità dell'aggiornamento 01.080: Cryo chamber e il nuovo modello del protagonista, con nuove animazioni."},
                @{@"Head": @"16 - Spot italiano per Puzzle & Dragons Z + Puzzle & Dragons: Super Mario Bros. Edition     0:34", @"subHead": @"Sfere colorate, puzzle, nemici da eliminare e mostri da collezionare. Ma, soprattutto, due giochi al prezzo di uno!"},
                @{@"Head": @"17 - Tomorrowland - 'Athena' Featurette     0:47", @"subHead": @"Meet Raffey Cassidy's character in the Disney sci-fi film."},
                @{@"Head": @"18 - Tomorrowland - 'Casey' Featurette     1:02", @"subHead": @"Britt Robinson plays the young heroine opposite George Clooney in the Disney sci-fi adventure."},
                @{@"Head": @"19 - Suicide Squad: Will Smith's Deadshot Look Revealed - IGN News     0:30", @"subHead": @"Newly revealed Suicide Squad set photos give us our first look at Will Smith as Floyd Lawton, aka Deadshot."},
                @{@"Head": @"20 - Halo 5 Won't Have Invasion Mode - IGN News     0:42", @"subHead": @"343 studio head Josh Holmes has confirmed Invasion mode isn't making a return in Halo 5: Guardians."},
                ];


    
    
    UITableView *tableView = (id)[self.view viewWithTag:1];
    [tableView registerClass:[CustomCell class] forCellReuseIdentifier:CellIdentifier];
    
    UITableView *tableView2 = (id)[self.view viewWithTag:2];
    [tableView2 registerClass:[CustomCell class] forCellReuseIdentifier:CellIdentifier];
    

}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.articles count];
}

- (NSInteger)tableView2:(UITableView *)tableView2 numberOfRowsInSection:(NSInteger)section {
    return [self.videos count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if ((id)[self.view viewWithTag:1]) {
        CustomCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        NSDictionary *rowData = self.articles[indexPath.row];
        cell.headline = rowData[@"Headline"];
        cell.subHeadline = rowData[@"subHeadline"];
        return cell;
    } else if ((id)[self.view viewWithTag:2]){
        CustomCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        NSDictionary *rowData = self.videos[indexPath.row];
        cell.headline = rowData[@"Head" ];
        cell.subHeadline = rowData[@"subHead"];
        return cell;
    }
    return nil;
}

@end
