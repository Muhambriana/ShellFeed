package com.mshell.shellfeed.utils

import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source

object DataDummy {
    fun generateDummyNews(): List<NewsDetail> {
        return listOf(
            NewsDetail(
                publishedAt = "2026-02-26T08:00:00Z",
                author = "John Smith",
                urlToImage = "https://picsum.photos/seed/news1/800/400",
                description = "NASA announces groundbreaking discovery of water on Mars surface, raising hopes for future colonization missions.",
                source = Source(name = "BBC News", id = "bbc-news"),
                title = "NASA Discovers Water on Mars Surface",
                url = "https://www.bbc.com/news/science-1",
                content = "Scientists at NASA have confirmed the presence of liquid water on the Martian surface. This landmark discovery opens new possibilities for human exploration and potential colonization of the Red Planet."
            ),
            NewsDetail(
                publishedAt = "2026-02-25T14:30:00Z",
                author = "Emily Johnson",
                urlToImage = "https://picsum.photos/seed/news2/800/400",
                description = "Global tech giants unveil a new AI framework that promises to revolutionize healthcare diagnostics worldwide.",
                source = Source(name = "TechCrunch", id = "techcrunch"),
                title = "New AI Framework Set to Transform Healthcare",
                url = "https://techcrunch.com/ai-healthcare-2",
                content = "A coalition of major technology companies has released a unified AI framework designed to assist doctors in diagnosing diseases earlier and more accurately than ever before."
            ),
            NewsDetail(
                publishedAt = "2026-02-24T10:15:00Z",
                author = "Michael Chen",
                urlToImage = "https://picsum.photos/seed/news3/800/400",
                description = "The World Economic Forum discusses the rapid growth of renewable energy adoption across developing nations.",
                source = Source(name = "Reuters", id = "reuters"),
                title = "Renewable Energy Adoption Surges in Developing Nations",
                url = "https://www.reuters.com/energy-3",
                content = "At the World Economic Forum, leaders highlighted the unprecedented pace at which developing nations are transitioning to renewable energy sources, with solar and wind leading the charge."
            ),
            NewsDetail(
                publishedAt = "2026-02-23T16:45:00Z",
                author = "Sarah Williams",
                urlToImage = "https://picsum.photos/seed/news4/800/400",
                description = "SpaceX successfully launches its largest rocket yet, carrying supplies for the International Space Station.",
                source = Source(name = "CNN", id = "cnn"),
                title = "SpaceX Launches Largest Rocket in History",
                url = "https://www.cnn.com/space-4",
                content = "SpaceX has achieved another milestone by successfully launching the Starship Super Heavy, the largest rocket ever built, on a resupply mission to the International Space Station."
            ),
            NewsDetail(
                publishedAt = "2026-02-22T09:00:00Z",
                author = "David Brown",
                urlToImage = "https://picsum.photos/seed/news5/800/400",
                description = "European Union introduces landmark legislation to regulate artificial intelligence across all member states.",
                source = Source(name = "The Guardian", id = "the-guardian"),
                title = "EU Passes Comprehensive AI Regulation Act",
                url = "https://www.theguardian.com/tech-5",
                content = "The European Parliament has approved the most comprehensive AI regulation in history, setting strict guidelines for how artificial intelligence can be developed and deployed across the continent."
            ),
            NewsDetail(
                publishedAt = "2026-02-21T12:20:00Z",
                author = "Jessica Lee",
                urlToImage = "https://picsum.photos/seed/news6/800/400",
                description = "Apple reveals its next-generation mixed reality headset with revolutionary holographic display technology.",
                source = Source(name = "The Verge", id = "the-verge"),
                title = "Apple Unveils Holographic Mixed Reality Headset",
                url = "https://www.theverge.com/apple-6",
                content = "Apple has taken the wraps off its second-generation Vision headset, featuring true holographic displays that project 3D images directly into the user's field of vision without the need for traditional screens."
            ),
            NewsDetail(
                publishedAt = "2026-02-20T18:30:00Z",
                author = "Robert Taylor",
                urlToImage = "https://picsum.photos/seed/news7/800/400",
                description = "Global stock markets reach record highs as inflation continues to cool across major economies.",
                source = Source(name = "Bloomberg", id = "bloomberg"),
                title = "Stock Markets Hit All-Time Highs Amid Cooling Inflation",
                url = "https://www.bloomberg.com/markets-7",
                content = "Major stock indices around the world have reached unprecedented levels as central banks signal potential rate cuts following months of declining inflation in the US, Europe, and Asia."
            ),
            NewsDetail(
                publishedAt = "2026-02-19T07:45:00Z",
                author = "Amanda Garcia",
                urlToImage = "https://picsum.photos/seed/news8/800/400",
                description = "Scientists achieve breakthrough in quantum computing, solving complex problems in seconds instead of years.",
                source = Source(name = "Wired", id = "wired"),
                title = "Quantum Computing Breakthrough Solves Previously Impossible Problems",
                url = "https://www.wired.com/quantum-8",
                content = "Researchers at MIT have demonstrated a quantum computer capable of solving optimization problems that would take classical supercomputers thousands of years, marking a new era in computational science."
            ),
            NewsDetail(
                publishedAt = "2026-02-18T15:10:00Z",
                author = "Thomas Anderson",
                urlToImage = "https://picsum.photos/seed/news9/800/400",
                description = "FIFA announces expansion of the World Cup to 64 teams starting from the 2034 tournament.",
                source = Source(name = "ESPN", id = "espn"),
                title = "FIFA Expands World Cup to 64 Teams",
                url = "https://www.espn.com/football-9",
                content = "FIFA has officially confirmed that the World Cup will feature 64 national teams starting from the 2034 edition, doubling the current format to allow greater representation from all confederations."
            ),
            NewsDetail(
                publishedAt = "2026-02-17T11:00:00Z",
                author = "Laura Martinez",
                urlToImage = "https://picsum.photos/seed/news10/800/400",
                description = "Electric vehicle sales surpass gasoline cars for the first time in European markets during January.",
                source = Source(name = "Financial Times", id = "financial-times"),
                title = "Electric Vehicles Outsell Gasoline Cars in Europe",
                url = "https://www.ft.com/ev-sales-10",
                content = "For the first time in automotive history, electric vehicle sales have exceeded those of traditional gasoline-powered cars across European markets, signaling a definitive shift in consumer preferences."
            ),
            NewsDetail(
                publishedAt = "2026-02-16T20:00:00Z",
                author = "Kevin Wilson",
                urlToImage = "https://picsum.photos/seed/news11/800/400",
                description = "Google DeepMind develops an AI model that can accurately predict earthquake locations up to a week in advance.",
                source = Source(name = "Nature", id = "nature"),
                title = "AI Can Now Predict Earthquakes a Week in Advance",
                url = "https://www.nature.com/earthquake-ai-11",
                content = "Google DeepMind's latest AI model has demonstrated the ability to predict the location and magnitude of earthquakes up to seven days before they occur, potentially saving millions of lives worldwide."
            ),
            NewsDetail(
                publishedAt = "2026-02-15T13:25:00Z",
                author = "Rachel Kim",
                urlToImage = "https://picsum.photos/seed/news12/800/400",
                description = "Netflix announces its first live sports broadcasting deal, securing rights to stream Olympic Games globally.",
                source = Source(name = "Variety", id = "variety"),
                title = "Netflix Secures Global Olympic Broadcasting Rights",
                url = "https://variety.com/netflix-olympics-12",
                content = "In a historic move, Netflix has signed a multi-billion dollar deal with the International Olympic Committee to stream the Olympic Games worldwide, marking the platform's first major live sports venture."
            ),
            NewsDetail(
                publishedAt = "2026-02-14T06:30:00Z",
                author = "Daniel Park",
                urlToImage = "https://picsum.photos/seed/news13/800/400",
                description = "Amazon launches drone delivery service in 10 new cities, promising 30-minute deliveries for Prime members.",
                source = Source(name = "CNBC", id = "cnbc"),
                title = "Amazon Drone Delivery Expands to 10 New Cities",
                url = "https://www.cnbc.com/amazon-drones-13",
                content = "Amazon has rolled out its Prime Air drone delivery service to ten additional metropolitan areas, bringing the total to 25 cities where customers can receive packages within 30 minutes of ordering."
            ),
            NewsDetail(
                publishedAt = "2026-02-13T17:50:00Z",
                author = "Olivia Thompson",
                urlToImage = "https://picsum.photos/seed/news14/800/400",
                description = "World Health Organization declares malaria eradicated in Southeast Asia following decades of global effort.",
                source = Source(name = "Al Jazeera", id = "al-jazeera"),
                title = "WHO Declares Malaria Eradicated in Southeast Asia",
                url = "https://www.aljazeera.com/health-14",
                content = "The World Health Organization has officially declared Southeast Asia free of malaria, marking a historic achievement in global public health after more than 30 years of coordinated eradication efforts."
            ),
            NewsDetail(
                publishedAt = "2026-02-12T22:15:00Z",
                author = "James Robinson",
                urlToImage = "https://picsum.photos/seed/news15/800/400",
                description = "Samsung unveils a foldable tablet that transforms into a laptop, challenging the traditional computing market.",
                source = Source(name = "Engadget", id = "engadget"),
                title = "Samsung Launches Revolutionary Foldable Tablet-Laptop Hybrid",
                url = "https://www.engadget.com/samsung-fold-15",
                content = "Samsung has introduced the Galaxy Fold Tab, a groundbreaking device that seamlessly transforms from a compact tablet into a full-sized laptop, featuring a flexible OLED display and desktop-grade performance."
            )
        )
    }
}