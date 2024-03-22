package com.vodafone.assignment.currentweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import com.vodafone.assignment.weatherdatatools.WeatherTextFormatter

@Composable
fun CurrentWeatherRoute(
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
    onDailyForecastClicked: () -> Unit,
    onChangeCityClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CurrentWeatherScreen(
        uiState = uiState,
        onDailyForecastClicked = onDailyForecastClicked,
        onChangeCityClicked = onChangeCityClicked
    )
}

@Composable
private fun CurrentWeatherScreen(
    uiState: CurrentWeatherUiState,
    onDailyForecastClicked: () -> Unit,
    onChangeCityClicked: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            LoadingContent()
        } else if (uiState.errorState.hasError) {
            EmptyContent(uiState.errorState.message)
        } else {
            Column {
                WeatherTopBar(title = "Today's Weather", onChangeCityClicked = onChangeCityClicked)
                WeatherData(
                    weather = uiState.weather,
                    onDailyForecastClicked = onDailyForecastClicked
                )
            }
        }
    }
}

@Composable
private fun WeatherData(
    weather: Weather,
    onDailyForecastClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            model = weather.iconUrl,
            contentDescription = "Weather condition icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(90.dp)
        )
        Text(weather.condition)
        Text(weather.description)
        Text("Temperature: ${WeatherTextFormatter.addCelsiusWithIntTemperature(weather.temperature)}")
        Text("Humidity: ${WeatherTextFormatter.addPercentageWithIntHumidity(weather.humidity)}")
        Text("Wind Speed: ${WeatherTextFormatter.addMPSWithIntWindSpeed(weather.windSpeed)}")

        Button(
            onClick = onDailyForecastClicked,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Show daily forecast")
        }
    }
}

@Preview
@Composable
private fun CurrentWeatherDataPreview() {
    Surface {
        val weather = Weather(
            day = "Friday",
            temperature = 30.0,
            humidity = 80.0,
            windSpeed = 20.0,
            condition = "Clear",
            description = "clear sky",
            iconUrl = "https://openweathermap.org/img/wn/01d@2x.png"
        )
        WeatherData(weather, {})
    }
}
