<template>

    <v-card outlined>
        <v-card-title>
            PassedReport # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="Name" v-model="item.name" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="SummationScore" v-model="item.summationScore" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="InterviewScore" v-model="item.interviewScore" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Position" v-model="item.position" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'PassedReportViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/passedReports/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

