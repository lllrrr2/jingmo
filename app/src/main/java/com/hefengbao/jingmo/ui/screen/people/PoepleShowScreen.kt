package com.hefengbao.jingmo.ui.screen.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.people.components.ShowPeoplePanel

@Composable
fun PeopleShowRoute(
    viewModel: PeopleShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val people by viewModel.people.collectAsState(initial = null)

    PeopleShowScreen(
        onBackClick = onBackClick,
        people = people
    )
}

@Composable
private fun PeopleShowScreen(
    onBackClick: () -> Unit,
    people: PeopleEntity?
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "人物资料"
    ) {
        people?.let {
            ShowPeoplePanel(people = it)
        }
    }
}