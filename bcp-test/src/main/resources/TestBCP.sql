CREATE TABLE [dbo].[TestBCP]([UUID] [nvarchar](10) NOT NULL,  
    [ColTinyint] [tinyint] NOT NULL,
    [ColSmallint] [smallint] NOT NULL,  
    [ColInt] [int] NOT NULL,  
    [ColBigint] [bigint] NOT NULL,  
    [ColString] [nvarchar](25) NOT NULL,  
	[ColFloat] [float] NOT NULL,  
	[ColDecimal] [decimal](18,5) NOT NULL,  
	[ColNumeric] [numeric](18,2) NOT NULL,  
	[ColDate] [date] NOT NULL,  
	[ColDatetime] [datetime] NOT NULL,  
 CONSTRAINT [PK_UUID] PRIMARY KEY CLUSTERED   
(  
    [UUID] ASC  
) ON [PRIMARY])