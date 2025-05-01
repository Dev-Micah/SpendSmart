package com.micahnyabuto.spendsmart.ui.Screens.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.micahnyabuto.spendsmart.R
import com.micahnyabuto.spendsmart.data.local.TransactionEntity
import com.micahnyabuto.spendsmart.ui.navigation.Destinations
import com.micahnyabuto.spendsmart.utils.CLOTHING
import com.micahnyabuto.spendsmart.utils.FOOD
import com.micahnyabuto.spendsmart.utils.SHELTER
import com.micahnyabuto.spendsmart.utils.TRANSACTION_FEES
import com.micahnyabuto.spendsmart.utils.TRANSPORT
import com.micahnyabuto.spendsmart.utils.categories
import com.micahnyabuto.spendsmart.utils.timestampToDateTime

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier =Modifier,
    viewModel: HomeViewModel = hiltViewModel()
){
   val allTransactions by viewModel.allTransactions.collectAsStateWithLifecycle(emptyList())
    val remainingBudget by viewModel.remainingBudget.collectAsState()

    val showSetMonthlyExpenseDialog by viewModel.showSetMonthlyExpenseDialog.collectAsState()

    var monthlyExpenseAmount by rememberSaveable { mutableStateOf("") }

    if(showSetMonthlyExpenseDialog){
        SetMonthlyExpenseDialog(
            amount = monthlyExpenseAmount,
            onAmountChange = {monthlyExpenseAmount = it},
            onSetMonthlyExpense = {viewModel.setMonthlyExpense(monthlyExpenseAmount)}
        )
    }
    HomeScreenContent(
        modifier = modifier.fillMaxSize(),
        allTransactions = allTransactions,
        remainingBudget = remainingBudget,
        onProfileClicked = {navController.navigate(Destinations.Profile)}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    allTransactions: List<TransactionEntity>,
    remainingBudget: String,
    modifier: Modifier =Modifier,
    onProfileClicked: () -> Unit
)
{
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .clickable {
                                onProfileClicked()
                            },
                        painter = painterResource(id = R.drawable.prof),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                    )
                },
                title = {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Welcome back, Meeks 👋",
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))


                    )
                }
            )
        },

    ) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerpadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {


            val totalAmountSpent =allTransactions.sumByDouble { it.amount }

            item {
                Surface (
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
                {
                    Box(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 16.dp,
                            )
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.secondary,
                                    )
                                ),
                                shape = MaterialTheme.shapes.medium
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append(remainingBudget)
                                    withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                        append( "Ksh.")
                                    }
                                },
                                style = MaterialTheme.typography.displayMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Black,
                                ),
                            )

                            Text(
                                text = "remaining on your monthly budget",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                            )
                        }
                    }
                }
            }
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal =16.dp,
                            vertical =16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Column {
                                Text(
                                    text = "Total Spend this Month",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.6f,
                                        )
                                    ),
                                )
                                Text(
                                    text = "Ksh $totalAmountSpent",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .border(
                                        width =1.dp,
                                        color =Color.Gray,
                                        shape = MaterialTheme.shapes.small
                                    )
                            ){
                                Row(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp,
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Month",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.6f,
                                            ),
                                        )
                                    )

                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Dropdown",
                                        modifier = Modifier
                                            .clickable{Toast.makeText(context, "This feature is currently unavailable",
                                                Toast.LENGTH_SHORT).show()},
                                        tint = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.6f,
                                        )
                                    )
                                }


                            }


                        }
                        val slices = categories.map { category ->
                            Slice(
                                value = allTransactions.filter {transaction -> transaction.category ==category.name }
                                    .sumByDouble {transaction ->
                                        transaction.amount * 100 / totalAmountSpent
                                    }
                                    .toFloat(),
                                color = category.color
                            )

                        }
                        StackedBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp),
                                    slices = slices
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            for (category in categories){
                                CategoriesUsageRow(
                                    color = category.color,
                                    title =category.name,
                                    value = when (category.name){
                                        FOOD -> "Ksh. ${
                                            allTransactions.filter {it.category == category.name}
                                                .sumByDouble { it.amount }
                                        }"
                                        TRANSPORT ->"ksh. ${
                                            allTransactions.filter { it.category == category.name }
                                                .sumByDouble { it.amount }
                                        }"
                                        CLOTHING->"ksh. ${
                                            allTransactions.filter { it.category == category.name }
                                                .sumByDouble { it.amount }
                                        }"
                                        SHELTER->"ksh. ${
                                            allTransactions.filter { it.category == category.name }
                                                .sumByDouble { it.amount }
                                        }"
                                        TRANSACTION_FEES->"ksh. ${
                                            allTransactions.filter { it.category == category.name }
                                                .sumByDouble { it.amount }
                                        }"

                                        else -> "ksh 0"
                                    }
                                )
                            }

                        }

                    }

                }
            }
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 16.dp,
                            ),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Recent Transactions",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 12.sp,
                                ),
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "View All",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 12.sp,
                                    ),
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_view_all),
                                    contentDescription = "View All",
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                        for (transaction in allTransactions) {
                            RecentTransactionRow(
                                budgetItem = transaction,
                            )
                        }
                    }

                }
            }

        }

    }

}
@Composable
fun CategoriesUsageRow(
    color: Color,
    title: String,
    value: String,
    modifier: Modifier =Modifier
){
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                ),
                modifier = Modifier,
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp,
            ),
            modifier = Modifier,
        )

    }

}
@Composable
fun RecentTransactionRow(
    budgetItem: TransactionEntity,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(
            width = 0.5.dp,
            color =MaterialTheme.colorScheme.onSurface.copy(
                alpha =0.1f
            )
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row (
            modifier = Modifier
                .padding(
                horizontal =16.dp,
                vertical =8.dp
            )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),

            ){
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    painter= when (budgetItem.category){
                        FOOD -> painterResource(id = R.drawable.food )
                        TRANSPORT -> painterResource(id = R.drawable.transport)
                        CLOTHING -> painterResource(id = R.drawable.clothing)
                        SHELTER -> painterResource(id = R.drawable.shelter )
                        TRANSACTION_FEES -> painterResource(id = R.drawable.transaction )
                        else -> painterResource(id = R.drawable.food)
                    },
                    contentDescription = "Expenses",
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = budgetItem.name,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                        ),
                    )

                    Text(
                        text = budgetItem.date.timestampToDateTime(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 9.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f,
                            )
                        ),
                    )
                }


            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Ksh. ${budgetItem.amount}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFD80000),
                        fontSize = 10.sp,
                    ),
                )

                Text(
                    text = budgetItem.category,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f,
                        )
                    ),
                )
            }

        }
    }


}
data class Slice(
    val value: Float,
    val color: Color
)
@Composable
private fun StackedBar(modifier: Modifier,slices: List<Slice>){
    Canvas(modifier = modifier) {
        val canvasWidth =size.width
        val canvasHeight= size.height

        var currentX =0f
        slices.forEachIndexed { _: Int, slice: Slice ->
            val width = (slice.value) /100f * canvasWidth
            drawRect(color = slice.color, topLeft = Offset(currentX ,0f) ,size = Size(
                width,
                canvasHeight
            )
            )
             currentX += width
        }
    }

}
@Composable
fun SetMonthlyExpenseDialog(
    amount: String,
    onAmountChange: (String) ->Unit,
    onSetMonthlyExpense: ()-> Unit,
    modifier: Modifier= Modifier
){
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = {

        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Monthly Expense",
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Set your monthly expense to help you track your spending",
                    style = MaterialTheme.typography.bodySmall,
                )

                OutlinedTextField(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    value = amount,
                    onValueChange = onAmountChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    placeholder = {
                        Text(
                            text = "15000",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f,
                                )
                            ),
                        )
                    },
                    textStyle = MaterialTheme.typography.bodySmall,
                )
            }
        },
        dismissButton = {},
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    modifier = Modifier.height(32.dp),
                    shape = RoundedCornerShape(50),
                    onClick = onSetMonthlyExpense,
                ) {
                    Text(
                        text = "Set Budget" ,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        },
    )

}
