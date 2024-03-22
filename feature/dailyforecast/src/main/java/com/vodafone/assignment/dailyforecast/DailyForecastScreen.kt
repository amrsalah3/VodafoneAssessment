package com.vodafone.assignment.dailyforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vodafone.assignment.core.presentation.EmptyContent
import com.vodafone.assignment.core.presentation.LoadingContent
import com.vodafone.assignment.core.presentation.WeatherTopBar
import com.vodafone.assignment.domain.weather.model.Weather


@Composable
fun DailyForecastRoute(
    viewModel: DailyForecastViewModel = hiltViewModel(),
    onChangeCityClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DailyForecastScreen(uiState = uiState, onChangeCityClicked = onChangeCityClicked)
}

@Composable
private fun DailyForecastScreen(
    uiState: DailyForecastUiState,
    onChangeCityClicked: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        WeatherTopBar(title = "Daily Forecast", onChangeCityClicked = onChangeCityClicked)
        when (uiState) {
            DailyForecastUiState.Loading -> LoadingContent()
            DailyForecastUiState.Empty -> EmptyContent("No available forecasts")
            is DailyForecastUiState.Forecast -> DailyForecastList(uiState.forecasts)
            is DailyForecastUiState.Error -> EmptyContent(uiState.message)
        }
    }
}

@Composable
private fun DailyForecastList(forecasts: List<Weather>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        items(forecasts.size) { index ->
            ForecastItem(forecasts[index])
        }
    }
}

@Composable
private fun ForecastItem(forecast: Weather, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = forecast.iconUrl,
            contentDescription = "Weather condition icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(64.dp)
        )

        Text(text = forecast.day, modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(forecast.condition)
            Text("${forecast.temperature}°C")
        }
    }
}

@Preview
@Composable
private fun DailyForecastListPreview() {
    Surface {
        val dailyForecast = listOf(
            Weather(
                "Friday",
                temperature = 30.0,
                humidity = 80.0,
                windSpeed = 20.0,
                condition = "Clear",
                description = "clear sky",
                iconUrl = "https://openweathermap.org/img/wn/01d@2x.png"
            ),
            Weather(
                "Saturday",
                temperature = 10.0,
                humidity = 40.0,
                windSpeed = 5.0,
                condition = "Windy",
                description = "much winds",
                iconUrl = "https://openweathermap.org/img/wn/01d@2x.png"
            )
        )
        DailyForecastList(dailyForecast)
    }
}
